sample
===
* 注释###

    select #use("cols")# from lb_user  where  #use("condition")#

cols
===
	id,username,password,role

updateSample
===

	id=#id#,username=#username#,password=#password#,role=#role#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(username)){
     and username=#username#
    @}
    @if(!isEmpty(password)){
     and password=#password#
    @}
    @if(!isEmpty(role)){
     and role=#role#
    @}
    
findUserByUsername
===
    select * from lb_user where username = #username#