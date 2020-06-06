package com.tensquare.frinend.service;


import com.tensquare.frinend.dao.FriendDao;
import com.tensquare.frinend.dao.NoFriendDao;
import com.tensquare.frinend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendService noFriendService;

    /**
     * 添加好友
     * @param userid
     * @param friendid
     * @return
     */
    public int addFriend(String userid, String friendid) {
        //先判断userid到friendid是否有数据，有的话返回0
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friend != null) return 0;
        //添加好友，让好友表中userid到friendid方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断friendid到userid是否有数据。如果有双方type都变为1
        if (friendDao.findByUseridAndFriendid(friendid, userid) != null) {
//            更新双方的isliske为1，表明双方相爱
            friendDao.updateIslike("1", userid, friendid);
            friendDao.updateIslike("1", friendid, userid);
        }
        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        //1.删除userid到friend的记录
        friendDao.deleteFriend(userid, friendid);
        //2.更新friend到user的islike为0
        friendDao.updateIslike("0", friendid, userid);
        //3.将userid到friendid的数据添加到非好友中
        noFriendService.addNoFriend(userid, friendid);
    }
}
