sample
===
* 注释###

    select #use("cols")# from lb_doctor  where  #use("condition")#

cols
===
	id,name,age,cert_id,sex,department,address,user_id,text,expert

updateSample
===

	id=#id#,name=#name#,age=#age#,cert_id=#certId#,sex=#sex#,department=#department#,address=#address#,user_id=#userId#,text=#text#,expert=#expert#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(name)){
     and name=#name#
    @}
    @if(!isEmpty(age)){
     and age=#age#
    @}
    @if(!isEmpty(certId)){
     and cert_id=#certId#
    @}
    @if(!isEmpty(sex)){
     and sex=#sex#
    @}
    @if(!isEmpty(department)){
     and department=#department#
    @}
    @if(!isEmpty(address)){
     and address=#address#
    @}
    @if(!isEmpty(userId)){
     and user_id=#userId#
    @}
    @if(!isEmpty(text)){
     and text=#text#
    @}
    @if(!isEmpty(expert)){
     and expert=#expert#
    @}
