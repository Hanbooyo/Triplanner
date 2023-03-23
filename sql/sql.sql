CREATE TABLE trip_schedule (
                               schedule_id NUMBER PRIMARY KEY,
                               name VARCHAR2(50),
                               start_date DATE,
                               end_date DATE,
                               user_id NUMBER,
                               FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE place (
                       place_id NUMBER PRIMARY KEY,
                       name VARCHAR2(50),
                       address VARCHAR2(100),
                       latitude NUMBER,
                       longitude NUMBER
);

CREATE TABLE schedule_place_mapping (
                                        mapping_id NUMBER PRIMARY KEY,
                                        schedule_id NUMBER,
                                        place_id NUMBER,
                                        FOREIGN KEY (schedule_id) REFERENCES trip_schedule (schedule_id),
                                        FOREIGN KEY (place_id) REFERENCES place (place_id)
);
