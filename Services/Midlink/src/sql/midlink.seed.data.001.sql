use midlink;

delete from command_template where command = 'LOCK';
insert into command_template (command, name_key, device_model, json_payload, needs_verification, admin_role, created_on, last_updated_on, label_key, risk, input_paramters)
	values ('LOCK', 'CAR_LOCKING', 'WILUS_PICCOLO_STX', '{"outputId":"1", "outputValue":"1"}', 0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'output1', 'HIGH', '[]');

delete from command_template where command = 'UNLOCK';
insert into command_template (command, name_key, device_model, json_payload, needs_verification, admin_role, created_on, last_updated_on, label_key, risk, input_paramters)
	values ('UNLOCK', 'CAR_LOCKING', 'WILUS_PICCOLO_STX', '{"outputId":"1", "outputValue":"0"}', 0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'output1', 'HIGH', '[]');

delete from command_template where command = 'RESET';
insert into command_template (command, name_key, device_model, json_payload, needs_verification, admin_role, created_on, last_updated_on, label_key, risk, input_paramters)
	values ('RESET', 'DEVICE_RESET', 'WILUS_PICCOLO_STX', '{}', 0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'output1', 'LOW', '[]');

delete from device_io_config where name_key = 'car.locking' and io_key = 'output1';
	insert into device_io_config (name_key, io_key, state)
	values ('CAR_LOCKING', 'output1', 'ENABLED');

delete from device_io_config where name_key = 'car.locking' and io_key = 'output2';
	insert into device_io_config (name_key, io_key, state)
	values ('CAR_LOCKING', 'output2', 'ENABLED');

delete from device_io_config where name_key = 'car.locking' and io_key = 'output3';
	insert into device_io_config (name_key, io_key, state)
	values ('CAR_LOCKING', 'output3', 'ENABLED');


use metadata;
update system_resource set properties = '{"port":"8082","url":"http://107.20.202.100", "SWITCH_DRYINPUT":"appMessage.ioService.dryInput5", "DIST_THRESHOLD":"30.48", "TIME_THRESHOLD":"360000", "CRON_EXP":"0 0/7 * * * ?", "db_username":"root","db_password":"naoChaqu","db_url":"jdbc:mysql://107.20.245.24:3306/midlink?characterEncoding=utf8","db_driver":"com.mysql.jdbc.Driver","max_active":"30","max_idle":"10"}'
where component_name = 'MIDLINK';

