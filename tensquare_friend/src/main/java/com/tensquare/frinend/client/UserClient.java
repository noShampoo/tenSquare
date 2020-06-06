package com.tensquare.frinend.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("tensquare-user")
public interface UserClient {

    /**
     * 更新我的关注数，和我关注的人的粉丝数
     * @param count
     * @param userid
     * @param friendid
     */
    @PutMapping("/user/updatefaf/{count}/{userid}/{friendid}")
    public void updateFanscountAndFllowcount(@PathVariable("count") int count,
                                             @PathVariable("userid") String userid,
                                             @PathVariable("friendid") String friendid);
}
