package com.tensquare.frinend.service;

import com.tensquare.frinend.dao.NoFriendDao;
import com.tensquare.frinend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoFriendService {

    @Autowired
    private NoFriendDao noFriendDao;

    /**
     * 添加非好友的记录,表示不喜欢，不要再推荐了
     * @param userid
     * @param friendid
     * @return
     */
    public int addNoFriend(String userid, String friendid) {
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if (noFriend != null) return 0;
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }
}
