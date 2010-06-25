--
-- OAUTH_TOKEN
--
create table is_oauth_tokens (
  "uid" varchar(150 BYTE) not null,
  gadget_url varchar(1024 BYTE) not null,
  gadget_url_key varchar(255 BYTE) not null,
  service_name varchar(255 BYTE) not null,
  access_token varchar(255 BYTE) not null,
  token_secret varchar(255 BYTE) not null,
  primary key ("uid", gadget_url_key, service_name)
);

--
-- OAUTH_CONSUMER
--
create table is_oauth_consumers (
  gadget_url varchar(1024 BYTE) not null,
  gadget_url_key varchar(255 BYTE) not null,
  service_name varchar(255 BYTE) not null,
  consumer_key varchar(255 BYTE),
  consumer_secret varchar(255 BYTE),
  signature_method varchar(20 BYTE),
  is_upload int(1) not null default 0,
  primary key (gadget_url_key, service_name)
);

--
-- OAUTH_CERTIFICATE
--
create table is_oauth_certificate (
  consumer_key varchar(255 BYTE) not null primary key,
  private_key clob,
  certificate clob
);