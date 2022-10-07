sample
===
* 注释###

    select #use("cols")# from lb_patient  where  #use("condition")#

cols
===
	id,name,age,cert_id,sex,address,hospitalization_id,drugsids,isout,appointment_id,user_id

updateSample
===

	id=#id#,name=#name#,age=#age#,cert_id=#certId#,sex=#sex#,address=#address#,hospitalization_id=#hospitalizationId#,drugsids=#drugsids#,isout=#isout#,appointment_id=#appointmentId#,user_id=#userId#

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
    @if(!isEmpty(address)){
     and address=#address#
    @}
    @if(!isEmpty(hospitalizationId)){
     and hospitalization_id=#hospitalizationId#
    @}
    @if(!isEmpty(drugsids)){
     and drugsids=#drugsids#
    @}
    @if(!isEmpty(isout)){
     and isout=#isout#
    @}
    @if(!isEmpty(appointmentId)){
     and appointment_id=#appointmentId#
    @}
    @if(!isEmpty(userId)){
     and user_id=#userId#
    @}

selectList
===
    SELECT
        #page("p.*,d.name as doctorName")#
    FROM
        lb_patient p
    LEFT JOIN lb_appointment a ON p.appointment_id = a.id
    LEFT JOIN lb_doctor d ON a.doctor_id = d.id where 1=1
        @if(!isEmpty(name)){
            and p.name=#name#
        @}
        @if(!isEmpty(certId)){
            and p.cert_id=#certId#
        @}
        @if(!isEmpty(certId)){
            and a.doctor_id=#doctorId#
        @}