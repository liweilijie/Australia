# mysql

php需要在php.ini配置文件里面打开mysql的扩展才能正常使用mysql

```sql
SELECT *
FROM
    wp_postmeta pm
WHERE
    meta_id NOT IN (
       SELECT max(meta_id) FROM  wp_postmeta pm2 where  pm2.post_id=pm.post_id and pm2.meta_key=pm.meta_key
    )
 
SELECT distinct meta_key From wp_postmeta Group By post_id,meta_key Having Count(*)>1

DELETE FROM wp_postmeta WHERE meta_key = '_edit_lock';
DELETE FROM wp_postmeta WHERE meta_key = '_edit_last';
DELETE FROM wp_postmeta WHERE meta_key = '_revision-control';
DELETE FROM wp_postmeta WHERE post_id NOT IN (SELECT post_id FROM wp_posts);
DELETE FROM wp_postmeta WHERE meta_key = '_wp_old_slug';
DELETE FROM wp_postmeta WHERE meta_key = '_revision-control';
DELETE FROM wp_postmeta WHERE meta_value = '{{unknown}}';

DELETE
FROM
    wp_postmeta
WHERE
meta_id  IN (
    select * from (
    select meta_id
    FROM
        wp_postmeta pm
    WHERE
        meta_id NOT IN (
           SELECT max(meta_id) FROM  wp_postmeta pm2 where  pm2.post_id=pm.post_id and pm2.meta_key=pm.meta_key
        )
    ) as g1
)

ALTER TABLE `wp_postmeta`
ADD UNIQUE INDEX `UNQ_meta_id` (`meta_id` ASC);
ALTER TABLE `wp_postmeta` DROP PRIMARY KEY;



ALTER TABLE `x_china_com_au`.`wp_postmeta` 
DROP INDEX `UNQ_meta_id`;

ALTER TABLE `wp_postmeta`
ADD PRIMARY KEY (`post_id`, `meta_key`);

ALTER TABLE `wp_postmeta`
CHANGE COLUMN `meta_key` `meta_key` VARCHAR(255) NOT NULL ,
CHANGE COLUMN `post_id` `post_id` BIGINT(20) UNSIGNED NOT NULL;

ALTER TABLE `wp_postmeta`
ADD UNIQUE INDEX `UNQ_post_id_meta_key` (`post_id` ASC, `meta_key` ASC),/* 这句可以加可以不加，因为已经是 PRIMARY KEY */
ADD UNIQUE INDEX `UNQ_meta_id_post_id_meta_key` (`meta_id` ASC, `post_id` ASC, `meta_key` ASC);

select min(id) from wp_posts;
select max(id) from wp_posts;
select count(*) from wp_posts;


ALTER TABLE wp_posts PARTITION BY RANGE(id) (
    PARTITION p0 VALUES LESS THAN (10000),
    PARTITION p1 VALUES LESS THAN (20000),
    PARTITION p2 VALUES LESS THAN (30000),
    PARTITION p3 VALUES LESS THAN (40000),
    PARTITION p4 VALUES LESS THAN (50000),
    PARTITION p5 VALUES LESS THAN (60000),
    PARTITION p6 VALUES LESS THAN (70000),
    PARTITION p7 VALUES LESS THAN (80000),
    PARTITION p8 VALUES LESS THAN (90000),
    PARTITION p9 VALUES LESS THAN (100000),
    PARTITION p10 VALUES LESS THAN (110000),
    PARTITION p11 VALUES LESS THAN (120000),
    PARTITION p12 VALUES LESS THAN (130000),
    PARTITION p13 VALUES LESS THAN (140000),
    PARTITION p14 VALUES LESS THAN (150000),
    PARTITION p15 VALUES LESS THAN (160000),
    PARTITION p16 VALUES LESS THAN (170000),
    PARTITION p17 VALUES LESS THAN (180000),
    PARTITION p18 VALUES LESS THAN (190000),
    PARTITION p19 VALUES LESS THAN (200000),
    PARTITION p20 VALUES LESS THAN (210000),
    PARTITION p21 VALUES LESS THAN (220000),
    PARTITION p22 VALUES LESS THAN (230000),
    PARTITION p23 VALUES LESS THAN (240000),
    PARTITION p24 VALUES LESS THAN (250000),
    PARTITION p25 VALUES LESS THAN (260000),
    PARTITION p26 VALUES LESS THAN (270000),
    PARTITION p27 VALUES LESS THAN (280000),
    PARTITION p28 VALUES LESS THAN (290000),
    PARTITION p29 VALUES LESS THAN (300000),
    PARTITION p30 VALUES LESS THAN (310000),
    PARTITION p31 VALUES LESS THAN (320000),
    PARTITION p32 VALUES LESS THAN (330000),
    PARTITION p33 VALUES LESS THAN (340000),
    PARTITION p34 VALUES LESS THAN (350000),
    PARTITION p35 VALUES LESS THAN (360000),
    PARTITION p36 VALUES LESS THAN (370000),
    PARTITION p37 VALUES LESS THAN (380000),
    PARTITION p38 VALUES LESS THAN (390000),
    PARTITION p39 VALUES LESS THAN (400000),
    PARTITION p40 VALUES LESS THAN (410000),
    PARTITION p41 VALUES LESS THAN (420000),
    PARTITION p42 VALUES LESS THAN (430000),
    PARTITION p43 VALUES LESS THAN (440000),
    PARTITION p44 VALUES LESS THAN (450000),
    PARTITION p45 VALUES LESS THAN (460000),
    PARTITION p46 VALUES LESS THAN (470000),
    PARTITION p47 VALUES LESS THAN (480000),
    PARTITION p48 VALUES LESS THAN (490000),
    PARTITION p49 VALUES LESS THAN (500000),
    PARTITION p50 VALUES LESS THAN MAXVALUE
    );

```