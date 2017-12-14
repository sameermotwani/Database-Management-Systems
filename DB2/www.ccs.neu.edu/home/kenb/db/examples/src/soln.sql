-- Problem 1: Show how to allow the user named 'Fred' to read all the data about rock bands.

-- RockBandView contains all band information for bands with style = rock

CREATE VIEW RockBandView
AS
SELECT b.id, b.name, b.style
FROM  Band b
WHERE b.style = 'rock'

-- Give permission for Fred to read data in the RockBandView
GRANT SELECT ON RockBandView TO Fred




-- Problem 2: The user named 'Mary' is the head of the popular music division. Give 'Mary' the ability to access and change the membership of pop bands, and let her delegate this authority to others. 

-- PopBandMembershipView contains all membership information for bands with style = pop

CREATE VIEW PopBandMembershipView
AS
SELECT m.person, m.band
FROM  Band b, memberOf m
WHERE b.style = 'pop'
  AND b.id = m.band

-- PopBandView contains all information for bands with style = pop

CREATE VIEW PopBandView
AS
SELECT b.id, b.name, b.style
FROM  Band b
WHERE b.style = 'pop'

-- PersonView contains all information for persons

CREATE VIEW PersonView
AS
SELECT p.id, p.name
FROM Person p

-- Give Mary permission to read data about PopBands and all Persons with permission to grant this to others.
-- This information is required so Mary can see what bands and persons she is altering the membership of.
GRANT SELECT ON PopBandView TO Mary WITH GRANT OPTION
GRANT SELECT ON PersonView TO Mary WITH GRANT OPTION

-- Give Mary permission to see, update, add, and delete membership information for PopBands with permission to grant this to others
GRANT SELECT, UPDATE, INSERT, DELETE ON PopBandMembershipView TO Mary WITH GRANT OPTION




-- Problem 3: Allow 'Yoko' to create new skiffle bands. 

-- SkiffleBandView contains all information for bands with style = 'skiffle'

CREATE VIEW SkiffleBandView
AS
SELECT b.id, b.name, b.style
FROM  Band b
WHERE b.style = 'skiffle'

-- BandIdView contains just ids for bands
CREATE VIEW BandIdView
AS
SELECT b.id
FROM Band b

-- Allow Yoko to read the ids of existing Bands
-- This information is required so Yoko can see which Ids are taken when she tries to add skiffle bands to avoid conflicting with the primary key
-- Did not want Yoko to see all information about bands since she should only have permission to add skiffle bands so just the ids are necessary

GRANT SELECT ON BandIdView TO Yoko

-- Allow Yoko to insert skiffle bands
GRANT INSERT ON SkiffleBandView TO Yoko
