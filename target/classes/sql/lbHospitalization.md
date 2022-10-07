sample
===
* 注释###

    select #use("cols")# from lb_hospitalization  where  #use("condition")#

cols
===
	id,floor,bed,door,medical_name,patient_id,intime,outtime

updateSample
===

	id=#id#,floor=#floor#,bed=#bed#,door=#door#,medical_name=#medicalName#,patient_id=#patientId#,intime=#intime#,outtime=#outtime#

condition
===
= 1
    @if(!isEmpty(id)){
     and id=#id#
    @}
    @if(!isEmpty(floor)){
     and floor=#floor#
    @}
    @if(!isEmpty(bed)){
     and bed=#bed#
    @}
    @if(!isEmpty(door)){
     and door=#door#
    @}
    @if(!isEmpty(medicalName)){
     and medical_name=#medicalName#
    @}
    @if(!isEmpty(patientId)){
     and patient_id=#patientId#
    @}
    @if(!isEmpty(intime)){
     and intime=#intime#
    @}
    @if(!isEmpty(outtime)){
     and outtime=#outtime#
    @}

selectList
===     
        SELECT
        	#page("h.*,p.name AS patientName")#
        FROM
            lb_hospitalization h
        LEFT JOIN lb_patient p ON h.patient_id = p.id where 1=1
            @if(!isEmpty(patientName)){
                and p.name like concat('%',#patientName#,'%')
            @}
           @if(!isEmpty(intime)){
                and h.intime=#intime#
           @}

selectAll
===     
        SELECT
        	h.*,p.name AS patientName
        FROM
            lb_hospitalization h
        LEFT JOIN lb_patient p ON h.patient_id = p.id
        
findOneByUserId
===     
        SELECT
            h.*,p.name AS patientName
        FROM
            lb_hospitalization h
        LEFT JOIN lb_patient p ON h.patient_id = p.id where p.user_id = #userId#