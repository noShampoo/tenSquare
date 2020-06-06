package com.tensquare.frinend.controller;

import com.tensquare.frinend.client.UserClient;
import com.tensquare.frinend.pojo.NoFriend;
import com.tensquare.frinend.service.FriendService;
import com.tensquare.frinend.service.NoFriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friedn")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private NoFriendService noFriendService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserClient userClient;

    @PutMapping("/like/{friendid}/{type}")
    public Result addFriend(@PathVariable("friendid") String friendid,
                            @PathVariable("type") String type) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.LOGINERROR, "非用户，无法完成操作");
        }
        String userid = claims.getId();
        if (type.equals("1")) {
            //添加朋友
            int flag = friendService.addFriend(userid, friendid);
//            if (flag == 1) {
//                return new Result(true, StatusCode.OK, "现在ta是你的朋友了");
//            }
            if (flag == 0) {
                return new Result(false, StatusCode.ERROR, "嘿，ta已经是你的朋友了");
            }
            userClient.updateFanscountAndFllowcount(1, userid, friendid);
            return new Result(true, StatusCode.OK, "现在ta是你的朋友了");
        } else if (type.equals("2")) {
            int flag = noFriendService.addNoFriend(userid, friendid);
            //添加非朋友(黑名单)
//            if (flag == 1) {
//                return new Result(true, StatusCode.OK, "现在你们两个分手了哦");
//            }
            if (flag == 0) {
                return new Result(false, StatusCode.ERROR, "嘿，她早都不是你的女朋友啦");
            }
            return new Result(true, StatusCode.OK, "现在你们两个分手了哦");
        } else {
            return new Result(false, StatusCode.ERROR, "参数错误");
        }
    }

    @DeleteMapping("{friendid}")
    public Result deleteFriend(@PathVariable("friendid") String friendid) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.LOGINERROR, "非用户，无法完成操作");
        }
        String userid = claims.getId();
        friendService.deleteFriend(userid, friendid);
        userClient.updateFanscountAndFllowcount(-1, userid, friendid);
        return new Result(true, StatusCode.OK, "删除好友成功");
    }


}
