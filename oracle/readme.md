![image-20200804114420041](image-20200804114420041.png)

https://cloud.tencent.com/developer/ask/68190

> select 
>    inst_id||' '||sid||','||serial# inst_sid_s#, 
>    username,
>    row_wait_obj#||','||row_wait_block#||','||row_wait_row# obj_lck,
>    blocking_session_Status||' '||blocking_instance||','||blocking_session blk_info,
>    final_blocking_session_Status||' '||final_blocking_instance||','||final_blocking_session f_blk_info,
>    event, 
>    seconds_in_wait 
> from 
>    gv$session 
> where 
>    lockwait is not null
> order by 
>    inst_id;

![image-20200804114543773](image-20200804114543773.png)

> select sql_id from v$session where username = 'COMMUNICATIONSFACADE62';

![image-20200804114558801](image-20200804114558801.png)

> select * from v$sql where sql_id = '2wtcc8ut1gsxs'

![image-20200804114609642](image-20200804114609642.png)



https://blog.51cto.com/zhangshaoxiong/1206277

> [root@bogon security]# netstat -na |grep ":7010" |awk '{++SS[$6]}END{for(a in SS) print a,SS[a]}'

![image-20200804114647238](image-20200804114647238.png)



https://yq.aliyun.com/articles/130142

![image-20200804114732977](image-20200804114732977.png)