sample
===
* 注释###

    select #use("cols")# from lb_seek  where  #use("condition")#

cols
===
	id,describes,illname,drugs,options,days,price,patient_id

updateSample
===

	id=#id#,describes=#describes#,illname=#illname#,drugs=#drugs#,options=#options#,days=#days#,price=#price#,patient_id=#patientId#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(describes)){
     and describes=#describes#
    @}
    @if(!isEmpty(illname)){
     and illname=#illname#
    @}
    @if(!isEmpty(drugs)){
     and drugs=#drugs#
    @}
    @if(!isEmpty(options)){
     and options=#options#
    @}
    @if(!isEmpty(days)){
     and days=#days#
    @}
    @if(!isEmpty(price)){
     and price=#price#
    @}
    @if(!isEmpty(patientId)){
     and patient_id=#patientId#
    @}
