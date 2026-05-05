INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 101, 'STANDARD', 5000, 2, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 101);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 102, 'DELUXE', 8000, 3, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 102);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 103, 'SUITE', 15000, 5, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 103);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 104, 'STANDARD', 5500, 2, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 104);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 105, 'DELUXE', 9000, 3, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 105);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 106, 'SUITE', 18000, 6, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 106);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 107, 'STANDARD', 6000, 2, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 107);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 108, 'DELUXE', 8500, 4, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 108);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 109, 'SUITE', 20000, 6, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 109);

INSERT INTO rooms (room_number, room_type, price, capacity, created_at)
SELECT 110, 'STANDARD', 5200, 2, NOW()
    WHERE NOT EXISTS (SELECT 1 FROM rooms WHERE room_number = 110);