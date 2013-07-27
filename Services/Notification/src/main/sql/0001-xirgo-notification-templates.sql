use notification;

insert into notification_template (locale, sender, subject, template, template_key, type, mime_type, senderName) 
values ('en_US', 'void', 'Xirgo sms set APN', '+XT:1002,{0},{1},{2}', 'xirgo.set.apn', 'sms', 'text', 'AnyTRAQ'),
('en_US', 'void', 'Xirgo sms set host/port', '+XT:1001,{0},{1},{2}', 'xirgo.set.host', 'sms', 'text', 'AnyTRAQ'),
('en_US', 'void', 'Xirgo sms query network setting', '+XT:1001,{0},{1},{2}', 'xirgo.qry.net.settings', 'sms', 'text', 'AnyTRAQ'),
('en_US', 'void', 'Xirgo sms report vehicle position', '+XT:7001,{0}', 'xirgo.report.veh.pos', 'sms', 'text', 'AnyTRAQ'),
('en_US', 'void', 'Xirgo sms sets ignition report interval', '+XT:3001,{0},{1}', 'xirgo.set.ign.interval', 'sms', 'text', 'AnyTRAQ');
