-- 创建subject表
CREATE TABLE IF NOT EXISTS `subject` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `name` varchar(100) NOT NULL COMMENT '课程名称',
  `description` text COMMENT '课程描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) COMMENT '课程名称唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';

-- 插入一些示例数据
INSERT INTO `subject` (`name`, `description`) VALUES
('Mathematics', 'Advanced mathematics course covering calculus and algebra'),
('Physics', 'Fundamental physics principles and applications'),
('Chemistry', 'Chemical reactions and molecular structures'),
('Biology', 'Study of living organisms and their interactions'),
('Computer Science', 'Programming, algorithms, and software development'),
('English Literature', 'Classic and contemporary English literature'),
('History', 'World history and historical events'),
('Geography', 'Physical and human geography studies'),
('Art', 'Creative arts and design principles'),
('Music', 'Musical theory and performance'); 