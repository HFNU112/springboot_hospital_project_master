sample
===
* 注释###

    select #use("cols")# from lb_medicalhistory  where  #use("condition")#

cols
===
	id,patient_id,name,time,hospitalization_id,doctor_id

updateSample
===

	id=#id#,patient_id=#patientId#,name=#name#,time=#time#,hospitalization_id=#hospitalizationId#,doctor_id=#doctorId#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(patientId)){
     and patient_id=#patientId#
    @}
    @if(!isEmpty(name)){
     and name=#name#
    @}
    @if(!isEmpty(time)){
     and time=#time#
    @}
    @if(!isEmpty(hospitalizationId)){
     and hospitalization_id=#hospitalizationId#
    @}
    @if(!isEmpty(doctorId)){
     and doctor_id=#doctorId#
    @}

selectList
===     
        SELECT
        	#page("m.*,p.name AS patientname,d.name AS doctorname,d.department AS department")#
        FROM
            lb_medicalhistory m
        LEFT JOIN lb_patient p ON m.patient_id = p.id
        LEFT JOIN lb_doctor d ON m.doctor_id = d.id where 1=1
            @if(!isEmpty(patientName)){
                and p.name like concat('%',#patientName#,'%')
            @}
            @if(!isEmpty(doctorName)){
                and d.name like concat('%',#doctorName#,'%')
            @}
            @if(!isEmpty(userId)){
                and p.user_id = #userId#
            @}
            @if(!isEmpty(patientId)){
             and m.patient_id=#patientId#
            @}