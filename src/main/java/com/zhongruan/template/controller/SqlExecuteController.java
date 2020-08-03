
package com.zhongruan.template.controller;

import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.SqlExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 16:25
 */
@RestController
@RequestMapping("/sqlExecute")
public class SqlExecuteController {
    @Autowired
    private SqlExecuteService sqlExecuteService;
@GetMapping("/sqlQuery")
    public ResultData sqlQuery(@RequestParam("sql") String sql){
        List list =new ArrayList();
     try{
          list = sqlExecuteService.sqlExecute(sql);

     }catch(Exception e){

     }
        return ResultData.success(list);
 }


}
