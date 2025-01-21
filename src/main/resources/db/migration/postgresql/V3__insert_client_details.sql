INSERT INTO pyramid_owner.OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('spring-security-oauth2-read-client', 'resource-server-rest-api', '$2a$10$47rr3U3lJkPoQnqwFacE9OFvs.wB98XUw35T3V9ASjnpyoDaiaiPu',
        'read', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);

INSERT INTO pyramid_owner.OAUTH_CLIENT_DETAILS(CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY)
VALUES ('spring-security-oauth2-read-write-client', 'resource-server-rest-api', '$2a$10$d5keVp4QHWQw2MKnt5adoOpQSXN5nkGvSQxnu23OdiBkkhey4IJ2q',
        'read,write', 'password,authorization_code,refresh_token,implicit', 'USER', 10800, 2592000);