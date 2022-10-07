sample
===
* 注释###

    select #use("cols")# from lb_appointment  where  #use("condition")#

cols
===
	id,patient_id,doctor_id,time,expenses

updateSample
===

	id=#id#,patient_id=#patientId#,doctor_id=#doctorId#,time=#time#,expenses=#expenses#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(patientId)){
     and patient_id=#patientId#
    @}
    @if(!isEmpty(doctorId)){
     and doctor_id=#doctorId#
    @}
    @if(!isEmpty(time)){
     and time=#time#
    @}
    @if(!isEmpty(expenses)){
     and expenses=#expenses#
    @}


selectList
===     
        SELECT
        	#page("a.*,p.name AS patientname,d.name AS doctorname,d.department AS department")#
        FROM
        	lb_appointment a
        LEFT JOIN lb_patient p ON a.patient_id = p.id
        LEFT JOIN lb_doctor d ON d.id = a.doctor_id where 1=1
            @if(!isEmpty(patientName)){
                and p.name like concat('%',#patientName#,'%')
            @}
            @if(!isEmpty(doctorName)){
                and d.name like concat('%',#doctorName#,'%')
            @}
            @if(!isEmpty(userId)){
                and p.user_id = #userId#
            @}
            
selectOne
===     
        SELECT
        	a.*,p.name AS patientname,d.name AS doctorname,d.department AS department
        FROM
        	lb_appointment a
        LEFT JOIN lb_patient p ON a.patient_id = p.id
        LEFT JOIN lb_doctor d ON d.id = a.doctor_id where a.id = #id#
        
selectListByDoctor
===     
        SELECT
        	#page("a.*,p.name AS patientname,d.name AS doctorname,d.department AS department")#
        FROM
        	lb_appointment a
        LEFT JOIN lb_patient p ON a.patient_id = p.id
        LEFT JOIN lb_doctor d ON d.id = a.doctor_id where 1=1
            @if(!isEmpty(patientName)){
                and p.name like concat('%',#patientName#,'%')
            @}
            @if(!isEmpty(time)){
                and a.time = #time#
            @}
            @if(!isEmpty(userId)){
                and d.user_id = #userId#
            @}