sample
===
* 注释###

    select #use("cols")# from lb_option  where  #use("condition")#

cols
===
	id,name,type,price

updateSample
===

	id=#id#,name=#name#,type=#type#,price=#price#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(name)){
     and name=#name#
    @}
    @if(!isEmpty(type)){
     and type=#type#
    @}
    @if(!isEmpty(price)){
     and price=#price#
    @}

getTotalPrice
===
    select sum(price) from lb_option where id in ( #join(ids)#)