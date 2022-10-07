sample
===
* 注释###

    select #use("cols")# from lb_drugs  where  #use("condition")#

cols
===
	id,name,type,price,number,text

updateSample
===

	id=#id#,name=#name#,type=#type#,price=#price#,number=#number#,text=#text#

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
    @if(!isEmpty(number)){
     and number=#number#
    @}
    @if(!isEmpty(text)){
     and text=#text#
    @}
