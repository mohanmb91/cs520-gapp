
    create table academics (
        id  serial not null,
        file_location_transcript varchar(255),
        gpa float4 not null,
        gre_score int8,
        tofel_score int8,
        primary key (id)
    );

    create table additional_department_feild_values (
        id  serial not null,
        feild_values varchar(255),
        application_id int4,
        feildDetails_id int4,
        primary key (id)
    );
     create table additional_department_feilds (
        id  serial not null,
        feild_type varchar(255),
        required boolean not null,
        required_feild varchar(255),
        department_id int4,
        primary key (id)
    );

    create table application_status (
        id  serial not null,
        status varchar(255),
        primary key (id)
    );

    create table applications (
        id  serial not null,
        cin varchar(255),
        citizenship varchar(255),
        date_of_birth varchar(255),
        email_id varchar(255),
        first_name varchar(255),
        gender varchar(255),
        isSubmitted boolean not null,
        last_name varchar(255),
        phone_number varchar(255),
        term varchar(255),
        user_name varchar(255),
        academics_id int4,
        program_id int4,
        student_id int4,
        primary key (id)
    );

    create table applications_additional_department_feild_values (
        applications_id int4 not null,
        additionalDepartmentFeildValues_id int4 not null
    );

    create table applications_education_background (
        applications_id int4 not null,
        educationBackgrounds_id int4 not null
    );

    create table applications_status_update (
        applications_id int4 not null,
        statusHistory_id int4 not null
    );

    create table departments (
        id  serial not null,
        dept_name varchar(255),
        primary key (id)
    );

    create table departments_additional_department_feilds (
        departments_id int4 not null,
        deptRequirements_id int4 not null
    );

    create table departments_programs (
        departments_id int4 not null,
        programs_id int4 not null
    );

    create table education_background (
        id  serial not null,
        college varchar(255),
        degree varchar(255),
        degree_earned varchar(255),
        from_date varchar(255),
        major varchar(255),
        to_date varchar(255),
        primary key (id)
    );

    create table programs (
        id  serial not null,
        name varchar(255),
        department_id int4,
        primary key (id)
    );

    create table status_update (
        id  serial not null,
        comment varchar(255),
        time_changed timestamp,
        application_id int4,
        currentStatus_id int4,
        user_id int4,
        primary key (id)
    );

    create table students (
        id  serial not null,
        cin varchar(255),
        citizenship varchar(255),
        date_of_birth varchar(255),
        email_id varchar(255),
        first_name varchar(255),
        gender varchar(255),
        last_name varchar(255),
        phone_number varchar(255),
        user_name varchar(255),
        user_id int4,
        primary key (id)
    );

    create table students_applications (
        students_id int4 not null,
        applications_id int4 not null
    );

    create table user_role (
        id  serial not null,
        role varchar(255),
        primary key (id)
    );

    create table users (
        id  serial not null,
        email_id varchar(255),
        enabled boolean not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        user_name varchar(255),
        student_id int4,
        userRole_id int4,
        primary key (id)
    );

    alter table applications_additional_department_feild_values 
        add constraint UK_7uxk7oex60223u768x799m5lr unique (additionalDepartmentFeildValues_id);

    alter table applications_education_background 
        add constraint UK_h4vnu579w6geqc60qxov71mrt unique (educationBackgrounds_id);

    alter table applications_status_update 
        add constraint UK_p0n317mofj6sokqmy32i4ev35 unique (statusHistory_id);

    alter table departments_additional_department_feilds 
        add constraint UK_hpodnjet8tlefm7oyi83ljeuq unique (deptRequirements_id);

    alter table departments_programs 
        add constraint UK_tf3r4r1fm5vxyqqj0cte1b242 unique (programs_id);

    alter table students_applications 
        add constraint UK_fnvbk1rqb7edo4uhj35hwcoan unique (applications_id);

    alter table additional_department_feild_values 
        add constraint FK_6tsgqtebtwoecuo44qf8pqoq 
        foreign key (application_id) 
        references applications;

    alter table additional_department_feild_values 
        add constraint FK_6517dubvl5huus5exft0m5g3j 
        foreign key (feildDetails_id) 
        references additional_department_feilds;

    alter table additional_department_feilds 
        add constraint FK_fddxsgb5uogywgpesi9tki0mf 
        foreign key (department_id) 
        references departments;

    alter table applications 
        add constraint FK_tb1kqefjp9qj9wphx12dptxkq 
        foreign key (academics_id) 
        references academics;

    alter table applications 
        add constraint FK_fvv8mt4q3l0jlgem0374rwfb5 
        foreign key (program_id) 
        references programs;

    alter table applications 
        add constraint FK_bjc0uvubm2oywqk7gpgdqigt1 
        foreign key (student_id) 
        references students;

    alter table applications_additional_department_feild_values 
        add constraint FK_7uxk7oex60223u768x799m5lr 
        foreign key (additionalDepartmentFeildValues_id) 
        references additional_department_feild_values;

    alter table applications_additional_department_feild_values 
        add constraint FK_mic64iweq3j141c5sbkshb1e5 
        foreign key (applications_id) 
        references applications;

    alter table applications_education_background 
        add constraint FK_h4vnu579w6geqc60qxov71mrt 
        foreign key (educationBackgrounds_id) 
        references education_background;

    alter table applications_education_background 
        add constraint FK_m4hwf9lra4qonvwspyao144r2 
        foreign key (applications_id) 
        references applications;

    alter table applications_status_update 
        add constraint FK_p0n317mofj6sokqmy32i4ev35 
        foreign key (statusHistory_id) 
        references status_update;

    alter table applications_status_update 
        add constraint FK_5pgqeyasb37pxfe1au9bqjfod 
        foreign key (applications_id) 
        references applications;

    alter table departments_additional_department_feilds 
        add constraint FK_hpodnjet8tlefm7oyi83ljeuq 
        foreign key (deptRequirements_id) 
        references additional_department_feilds;

    alter table departments_additional_department_feilds 
        add constraint FK_kl23bke6bqt8ujo53h219s470 
        foreign key (departments_id) 
        references departments;

    alter table departments_programs 
        add constraint FK_tf3r4r1fm5vxyqqj0cte1b242 
        foreign key (programs_id) 
        references programs;

    alter table departments_programs 
        add constraint FK_rriiddsas5pqioblauvbw5d60 
        foreign key (departments_id) 
        references departments;

    alter table programs 
        add constraint FK_t38cee5jtiwtw07papp2rjlca 
        foreign key (department_id) 
        references departments;

    alter table status_update 
        add constraint FK_qujt4uvkb6svrqpw9kgwx3i3u 
        foreign key (application_id) 
        references applications;

    alter table status_update 
        add constraint FK_7pfsrgtubeautp2itt7lax3sr 
        foreign key (currentStatus_id) 
        references application_status;

    alter table status_update 
        add constraint FK_42uds9vyjqjboer2i4pwjsq3p 
        foreign key (user_id) 
        references users;

    alter table students 
        add constraint FK_g4fwvutq09fjdlb4bb0byp7t 
        foreign key (user_id) 
        references users;

    alter table students_applications 
        add constraint FK_fnvbk1rqb7edo4uhj35hwcoan 
        foreign key (applications_id) 
        references applications;

    alter table students_applications 
        add constraint FK_hecpbi1tgo32hmu1q5ih2te0 
        foreign key (students_id) 
        references students;

    alter table users 
        add constraint FK_qh3otyipv2k9hqte4a1abcyhq 
        foreign key (student_id) 
        references students;

    alter table users 
        add constraint FK_pxsi5ryp0d78pvm0yhq4x51mw 
        foreign key (userRole_id) 
        references user_role;
