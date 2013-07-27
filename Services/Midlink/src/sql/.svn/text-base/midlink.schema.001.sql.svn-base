CREATE DATABASE midlink;

use midlink;

drop table if exist device_state;
create table device_state (
  id INT UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
  jsonDetails varchar(1024), 
  last_activity_ts timestamp not null, 
  lat FLOAT(14,4) not null default 0.0, 
  lng FLOAT(14,4) not null default 0.0, 
  speed FLOAT(8,2) not null default 0.0, 
  state varchar(64) not null, 
  unit_address varchar(128) not null unique
) ENGINE=INNODB;

drop table if exists command_template;
create table command_template (
  id INT UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
  command varchar(128) not null,
  name_key varchar(128) not null,
  device_model varchar(128) not null,
  json_payload varchar(1024), 
  needs_verification TINYINT DEFAULT 0,
  admin_role TINYINT DEFAULT 0,
  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  last_updated_on TIMESTAMP,
  label_key varchar(128),
  risk varchar(128),
  input_paramters varchar(1024)
) ENGINE=INNODB;
CREATE INDEX command_template_indx1 ON command_template (command, device_model);

drop table if exists device_io_config;
create table device_io_config (
  id INT UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name_key varchar(128) not null,
  io_key varchar(128) not null,
  state varchar(48)
) ENGINE=INNODB;
CREATE INDEX device_io_config_indx1 ON device_io_config (io_key);

drop table if exists command_event;
create table command_event (
  id INT UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
  command varchar(128) not null,
  device_model varchar(128) not null,
  device_id varchar(256),
  json_payload varchar(1024), 
  input_parameters varchar(1024), 
  needs_verification TINYINT DEFAULT 0,
  raw_payload varchar(1024), 
  state varchar(48),
  created_on TIMESTAMP,
  last_updated_on TIMESTAMP,
  retries_delivery TINYINT,
  retries_verify TINYINT,
  scheduled_for TIMESTAMP,
  failure_reason varchar(1024),
  message_id INT not null,
  account_user_id INT,
  affiliate_user_id INT,
  risk varchar(128),
  security_code varchar(48)
) ENGINE=INNODB;
CREATE INDEX command_event_indx1 ON command_event (command, device_model);
