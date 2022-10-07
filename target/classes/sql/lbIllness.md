sample
===
* 注释###

    select #use("cols")# from lb_illness  where  #use("condition")#

cols
===
	id,name,text

updateSample
===

	id=#id#,name=#name#,text=#text#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(name)){
     and name=#name#
    @}
    @if(!isEmpty(text)){
     and text=#text#
    @}
