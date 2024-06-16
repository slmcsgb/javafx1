DROP DATABASE IF EXISTS crops;

CREATE DATABASE IF NOT EXISTS crops;

USE crops;

CREATE TABLE IF NOT EXISTS crop_production (
    id INT AUTO_INCREMENT PRIMARY KEY,
    crop_name VARCHAR(255) NOT NULL,
    description TEXT,
    
    production_quantity DOUBLE
);

INSERT INTO crop_production (crop_name, description, production_quantity) VALUES
('Rice', 'Rice is a staple food for a large part of the world’s human population, especially in Asia.', 20000.0),
('Wheat', 'Wheat is a grass widely cultivated for its seed, a cereal grain which is a worldwide staple food.', 15000.0),
('Maize', 'Maize, also known as corn, is a cereal grain first domesticated by indigenous peoples in southern Mexico.', 12000.0),
('Sugarcane', 'Sugarcane is a tropical, perennial grass that forms lateral shoots at the base to produce multiple stems.', 18000.0),
('Cotton', 'Cotton is a soft, fluffy staple fiber that grows in a boll, or protective case, around the seeds of the cotton plants.', 8000.0),
('Barley', 'Barley is a member of the grass family, a major cereal grain grown in temperate climates globally.', 6000.0),
('Soybean', 'Soybean is a species of legume native to East Asia, widely grown for its edible bean which has numerous uses.', 7000.0),
('Sorghum', 'Sorghum is a genus of flowering plants in the grass family, some of which are cultivated as cereals for human consumption and animal feed.', 5000.0),
('Millet', 'Millet is a group of highly variable small-seeded grasses, widely grown around the world as cereal crops or grains for fodder and human food.', 4000.0),
('Oats', 'Oats are a species of cereal grain grown for its seed, which is known by the same name.', 4500.0),
('Rye', 'Rye is a grass grown extensively as a grain, a cover crop and as a forage crop.', 3500.0),
('Peanut', 'Peanut, also known as groundnut, is a legume crop grown mainly for its edible seeds.', 3000.0),
('Sunflower', 'Sunflower is an annual plant in the family Asteraceae, with a large flower head (inflorescence).', 3200.0),
('Rapeseed', 'Rapeseed, also known as rape, oilseed rape, is a bright-yellow flowering member of the family Brassicaceae (mustard or cabbage family).', 2700.0),
('Cassava', 'Cassava, also called manioc, is a woody shrub native to South America of the spurge family, Euphorbiaceae.', 2500.0),
('Potato', 'The potato is a root vegetable, a starchy tuber of the plant Solanum tuberosum, and is a staple food in many parts of the world.', 21000.0),
('Tomato', 'The tomato is the edible berry of the plant Solanum lycopersicum, commonly known as a tomato plant.', 22000.0),
('Cabbage', 'Cabbage is a leafy green, red, or white biennial plant grown as an annual vegetable crop for its dense-leaved heads.', 17000.0),
('Onion', 'The onion is a vegetable that is the most widely cultivated species of the genus Allium.', 19000.0),
('Garlic', 'Garlic is a species in the onion genus, Allium. Its close relatives include the onion, shallot, leek, chive, and Chinese onion.', 23000.0);

