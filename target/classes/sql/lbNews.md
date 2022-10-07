sample
===
* 注释###

    select #use("cols")# from lb_news  where  #use("condition")#

cols
===
	id,title,content,create_time

updateSample
===

	id=#id#,title=#title#,content=#content#,create_time=#createTime#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(title)){
     and title=#title#
    @}
    @if(!isEmpty(content)){
     and content=#content#
    @}
    @if(!isEmpty(createTime)){
     and create_time=#createTime#
    @}
