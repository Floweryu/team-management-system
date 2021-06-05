/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : team_manage_system

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 05/06/2021 13:48:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_achievement
-- ----------------------------
DROP TABLE IF EXISTS `t_achievement`;
CREATE TABLE `t_achievement`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章名',
  `magazine_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '期刊名',
  `submit_time` date NULL DEFAULT NULL COMMENT '投稿时间',
  `check_time` date NULL DEFAULT NULL COMMENT '审核时间',
  `receive_time` date NULL DEFAULT NULL COMMENT '接收时间',
  `cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '花费(单位：元)',
  `reward` decimal(10, 2) NULL DEFAULT NULL COMMENT '奖励(单位：元)',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '投稿人',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `by_user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '信息上传修改用户',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_achievement
-- ----------------------------
INSERT INTO `t_achievement` VALUES (1, '现代高校智能机房建设', '北化', '2021-05-13', '2021-05-17', '2021-05-20', 0.00, 20.30, '2017040330', '', '2017040330', '2021-05-10 17:14:43', '2021-05-20 19:54:47');
INSERT INTO `t_achievement` VALUES (5, '科学期刊', NULL, '2021-05-04', '2021-05-11', '2021-05-29', 0.00, 10.00, '2017040329', NULL, '2017040329', '2021-05-31 14:46:19', NULL);

-- ----------------------------
-- Table structure for t_assets
-- ----------------------------
DROP TABLE IF EXISTS `t_assets`;
CREATE TABLE `t_assets`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '资产编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '资产名称',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '使用者id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `by_user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '上传者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_assets
-- ----------------------------
INSERT INTO `t_assets` VALUES (1, '20200510', '电脑', '2017040329', '实验室使用', '2017040330', '2021-05-10 22:54:05', '2021-05-20 20:01:58');
INSERT INTO `t_assets` VALUES (5, '20210520', '电脑显示屏', '2017040330', NULL, '2017040330', '2021-05-20 20:02:23', NULL);

-- ----------------------------
-- Table structure for t_class
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '课表名称',
  `store_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '存储路径',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户账号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_class
-- ----------------------------
INSERT INTO `t_class` VALUES (1, '大四下学期', '/resource/class/张俊峰(2020-2021-2)课表.pdf', '2017040330', '2021-05-19 10:10:15', NULL);
INSERT INTO `t_class` VALUES (2, '大三下学期', '/resource/class/张俊峰(2019-2020-2)课表.pdf', '2017040330', '2021-05-19 10:10:15', NULL);
INSERT INTO `t_class` VALUES (3, '大三上学期', '', '2017040330', '2021-05-19 10:10:15', NULL);

-- ----------------------------
-- Table structure for t_classify
-- ----------------------------
DROP TABLE IF EXISTS `t_classify`;
CREATE TABLE `t_classify`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分类名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分类描述',
  `by_user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '管理者id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_classify
-- ----------------------------
INSERT INTO `t_classify` VALUES (1, 'Redis', NULL, '2017040330', '2021-05-01 23:27:11', '2021-05-23 13:15:21');
INSERT INTO `t_classify` VALUES (2, '科技类', NULL, '2017040330', '2021-05-02 22:04:30', '2021-05-23 13:15:26');
INSERT INTO `t_classify` VALUES (3, '生物类', NULL, '2017040330', '2021-05-02 22:04:44', '2021-05-23 13:15:30');
INSERT INTO `t_classify` VALUES (4, '计算机类', '计算机类文献', '2017040330', '2021-05-23 11:43:01', '2021-05-23 14:02:29');
INSERT INTO `t_classify` VALUES (5, 'Spring Boot', '关于Spring Boot开发的文献', '2017040330', '2021-05-23 14:02:13', NULL);
INSERT INTO `t_classify` VALUES (9, '生物', '关于生物方面论文研究', '2017040329', '2021-05-31 14:34:32', NULL);
INSERT INTO `t_classify` VALUES (10, '农业', '关于工业方面论文', '2017040329', '2021-05-31 14:34:44', NULL);
INSERT INTO `t_classify` VALUES (11, '化学', '关于化学方面研究', '2017040329', '2021-05-31 14:35:14', NULL);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `topic_id` bigint(0) NOT NULL COMMENT '评论对象id',
  `topic_type` tinyint(0) NOT NULL COMMENT '评论主题: 1: 文献, 2: 组会, 3: 报告',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '评论内容',
  `from_uid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '评论者id',
  `to_uid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '目标用户id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '评论日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_comment_ibfk_1`(`topic_id`) USING BTREE,
  INDEX `t_comment_ibfk_2`(`from_uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (8, 2, 1, 'nknk', '2017040330', '', '2021-05-14 15:02:41');
INSERT INTO `t_comment` VALUES (9, 5, 1, '文章很有参考价值', '2017040330', '', '2021-05-20 15:03:16');
INSERT INTO `t_comment` VALUES (10, 9, 1, '写的挺好的', '2017040329', '', '2021-05-31 14:51:17');
INSERT INTO `t_comment` VALUES (11, 13, 1, '有理有据', '2017040329', '', '2021-05-31 14:51:35');
INSERT INTO `t_comment` VALUES (12, 11, 1, '不错不错', '2017040329', '', '2021-05-31 14:51:53');
INSERT INTO `t_comment` VALUES (13, 10, 1, '大大', '2017040329', '', '2021-05-31 14:52:09');
INSERT INTO `t_comment` VALUES (14, 7, 1, '很值得看', '2017040329', '', '2021-05-31 14:52:23');
INSERT INTO `t_comment` VALUES (15, 12, 1, '翻译的一般', '2017040329', '', '2021-05-31 14:52:36');

-- ----------------------------
-- Table structure for t_document
-- ----------------------------
DROP TABLE IF EXISTS `t_document`;
CREATE TABLE `t_document`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文献标题',
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '作者',
  `publish_time` date NOT NULL COMMENT '发表时间',
  `introduction` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文献简介',
  `publish_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '出版单位',
  `size` double NULL DEFAULT NULL COMMENT '文献大小单位:(M)',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '文件名',
  `store_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '存储位置',
  `upload_user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '上传用户',
  `origin_address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '来源地址',
  `like_count` bigint(0) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `average_score` double NOT NULL DEFAULT 0 COMMENT '评分',
  `view_count` bigint(0) NOT NULL DEFAULT 0 COMMENT '浏览量',
  `download_count` bigint(0) NOT NULL DEFAULT 0 COMMENT '下载次数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_document
-- ----------------------------
INSERT INTO `t_document` VALUES (5, '基于SSM的权限管理系统及数据可视化', '张卫华', '2020-10-09', '近年来,随着互联网技术飞速发展,互联网的各种应用已经渗透到社会的各个领域,并发挥着巨大的作用,改变了人们的生活方式、工作方式、乃至思维方式。越来越多的组织引入互联网相关技术来提升工作和决策效率,开发出了各种软件应用系统,使得系统的权限管理变得至关重要。虽然Apache Shiro与Spring Security两个权限框架有较为广泛的使用,但是在现实应用中却缺乏灵活性。本论文基于扩展的RBAC模型,充分考虑到软件系统的易用性和灵活性,设计并实现了一套权限管理系统。主要研究内容如下:1.基于对权限控制的研究以及对权限管理框架Apache Shiro与Spring Security的原理分析,对实现权限管理系统展开了详细的需求分析和设计。从功能性需求和非功能性需求两大角度进行分析,其中系统后台需求分为了部门模块、用户模块、权限模块、角色模块、角色权限模块、角色用户模块、权限更新操作记录模块、数据可视化模块这八大模块,对数据库表进行了详细的设计与分析,同时对系统的整体架构进行了设计。2.根据设计的功能模块,逐一对其具体实现。该系统采用了 Web开发中常用的SSM+Tomcat+MySQL+Redis的组合构建前后端的逻辑。分别实现了:登录功能、权限拦截功能、分页功能、权限缓存功能、权限更新日志记录功能、权限操作回滚功能、重要信息的图表展示功能、以及部门、用户、角色、权限的维护功能。该系统基于扩展的RBAC模型实现了细粒度的权限控制,通过前端页面的精心设计使用户可以高效灵活地进行权限管理。', '北京邮电大学', 6, '基于SSM的权限管理系统及数据可视化_张卫华.caj', '/resource/document/基于SSM的权限管理系统及数据可视化_张卫华.caj', '2017040330', 'https://w.buct.edu.cn/https/77726476706e69737468656265737421fbf952d2243e635930068cb8/kcms/detail/detail.aspx?dbcode=CMFD&dbname=CMFDTEMP&filename=1021024490.nh&v=h4bp96DOWRIIYdqF%25mmd2BDJAiC5yfm2fIG8k86mszfIc%25mmd2BzuhB3Qr%25mmd2FwPidb5ZIe76OPe1', 1, 5, 11, 2, '2021-05-20 13:52:15', '2021-05-25 21:25:25');
INSERT INTO `t_document` VALUES (6, '基于前后端分离技术的web开发框架设计', '吴昌政', '2020-02-08', 'Web开发框架是一种“半成品”,封装应用的基础能力,如访问数据库、访问消息队列和缓存、安全认证等。企业应用基于开发框架进行二次开发可避免从零开始,降低建设成本,减少新业务上线时间。运营商大量的IT系统基于传统的“一体化”开发框架,这些系统建设初期效率较高,经过长期的运营,出现一系列问题:（1）随着功能的扩展,系统越来越庞大、维护困难、发布周期长。（2）系统能力不开放,不能被其他系统快速使用,涉及跨系统交互的需求支撑效率低下。（3）单系统建设运营团队能力存在瓶颈,不能很好兼顾用户使用体验提升和业务逻辑的优化。为此,运营商IT架构正在向“平台+应用”的模式演进,通过微服务架构分解为一系列子模块,形成一系列能力中心,敏捷支撑新的应用场景。为适应新的模式,新一代的Web应用要具备以下特点:（1）微服务架构:系统被分解为一系列可独立部署的子模块,但对用户来说是一个整体。（2）开放性:系统的能力通过restful协议开放,支持远程访问,对周边系统透明。（3）前后端分离:支持用户交互界面和业务逻辑被划分到不同的子模块中,独立建设和运营。（4）可扩展性:支持通过增加模块的方式扩展系统的功能,不改变现有模块的部署。（5）安全性:保护开放的能力免受非授权访问,支持用户一点登陆,所有子模块通过认证。（6）云原生:系统能够在容器中部署,支持集中监控所有模块的运行状态,支持基于容器技术一键发布、负载均衡、动态伸缩。（7）灰度发布:每个模块均可根据工号配置不同的版本,达到无风险发布的目的。本文通过对新一代Web应用的主流技术的分析、比对、选型和集成,实现了一套满足上述需求的开发框架,具备员工工号管理、权限管理、角色管理、安全认证、模块管理、菜单管理、公告管理、部署环境管理等基础功能,详细介绍了系统总体架构、功能架构、技术架构、数据模型、功能实现和实现效果。相比于传统框架,新一代Web开发框架具有轻量级、开放、可扩展、云原生的特点。通过对新一代Web开发框架的研究,从实操层面对“平台+应用”的演进思路进行POC验证,为企业传统IT系统演进提供技术模板支持,减少新应用的上线时间,提升运营效率。', '南京邮电大学', 4, '基于前后端分离技术的web开发框架设计_吴昌政.caj', '/resource/document/基于前后端分离技术的web开发框架设计_吴昌政.caj', '2017040330', 'https://w.buct.edu.cn/https/77726476706e69737468656265737421fbf952d2243e635930068cb8/kcms/detail/detail.aspx?dbcode=CMFD&dbname=CMFD202101&filename=1020427199.nh&v=Me4SOlRRSkBJ2bhl7Zdf%25mmd2BpneIkx0Duscbu1YZUt5QdoBODavdksIDkrrKvfpeb8y', 1, 5, 9, 4, '2021-05-20 14:58:40', '2021-05-25 21:29:50');
INSERT INTO `t_document` VALUES (7, '单幅图像去雾算法的研究', '王雪梅', '2019-05-01', '随着计算机技术与图像处理技术的发展,计算机视觉被广泛应用到智能交通、遥感遥测、小区监控、自动驾驶等多个领域中。然而,工作在户外的计算机视觉系统在采集图像的过程中,容易受到外界环境的干扰。特别在雾霾天气下,采集的户外图像往往退化严重,主要表现为清晰度下降、色彩失真等,而将特征受损的图像作为输入必然会影响系统后续对图像的分析和理解,削弱视觉系统的工作性能。因此,研究如何有效地从有雾降质图像中重建出原始图像,对提高视觉系统的应用性能和鲁棒性具有重要的现实意义。本质上,图像去雾的目的在于从退化图像中去除来自天气因素的干扰,增强图像的清晰程度、颜色饱和度,最大限度地恢复图像有用的特征。论文的主要工作是在分析单幅有雾图像特征的基础上,结合雾天成像的机理,从图像增强和图像复原两个方面,分别设计对应的单幅图像去雾算法。主要内容包括:（1）针对传统图像增强类算法在图像去雾的应用上表现出的局限性,提出基于场景雾化程度估计的有雾图像增强算法。算法充分挖掘有雾图像在亮度、梯度、饱和度等方面的特征衰减与景物深度分布之间的联系,利用雾图多特征先验对场景雾化程度进行估计。再基于全变分优化的数学模型,从原始雾图中分离出有效的纹理分量,并根据估计得到的雾化程度分布,对图像的纹理分量和色彩饱和度分别执行自适应增强和校正处理。实验结果表明,本算法能够较好恢复雾化场景的特征,在提高清晰度和色彩保真两方面均取得不错的主观效果。（2）为解决户外图像天空区域暗通道先验失效的问题,提出基于大气散射模型的有雾图像复原算法。算法充分利用天空部分在亮度、连通性、位置、概率分布等方面的特征,设计出天空区域识别方法,并将天空识别结果用于全局大气光的估计和透射率下限的自动调节,以避免天空部分透射率的过低估计,防止复原天空部分色彩失真;为减少高分辨率图像在透射率估计方面的计算开销,本算法依据暗通道先验,利用降采样后的有雾图像进行透射率的初步估计,并应用最小滤波和联合双边滤波对透射图进行进一步的优化处理。实验结果表明,采用本算法估计得到的透射率分布在保留场景结构、保持景深一致区域的局部平滑性方面具有较好的效果,复原图像中远景更加清晰,天空区域色彩保真度高,算法实时性能较好。（3）针对最小滤波模板尺寸参数难以统一设置的问题,提出基于多尺度融合和全变分优化的透射率估计的优化算法。算法首先利用暗通道先验,根据不同的滤波模板尺寸,从输入原始雾图中导出两幅透射粗估计图;然后,对两幅透射图粗估计,执行拉普拉斯金字塔分解,在不同分解层上,依据两者在边缘、纹理保留上的差异性,执行逐层加权融合处理;最后,利用全变分模型对透射图执行局部平滑优化,进一步消除透射图中高频纹理噪声分量,并应用基于梯度近似的迭代方法加速优化模型的求解。另外,算法依据景深分布与雾气浓度的一致性变化关系,利用场景中浓雾区域的多先验特征,通过定位场景最深像素点位置实现全局大气光的估计,从而在一定程度上减少高亮物体对估计大气光带来的不良影响。实验结果表明,利用本算法复原后图像在整体对比度增强、细节保留、颜色饱和度恢复等方面均取得较好的效果。', '南京邮电大学', 7, '单幅图像去雾算法的研究_王雪梅.caj', '/resource/document/单幅图像去雾算法的研究_王雪梅.caj', '2017040330', 'https://w.buct.edu.cn/https/77726476706e69737468656265737421fbf952d2243e635930068cb8/kcms/detail/detail.aspx?dbcode=CDFD&dbname=CDFDLAST2021&filename=1020427772.nh&v=w6IBw4hCDj3OGngUqM21AaxndhtFl%25mmd2F89V0jW2hk9OMCvHaMNjceZgNdoNtwHkLgn', 1, 4, 4, 1, '2021-05-31 13:43:19', '2021-06-02 10:44:20');
INSERT INTO `t_document` VALUES (9, '基于特征融合与对抗遮挡网络的目标检测算法设计与实现', '周旺', '2020-05-15', '目标检测是计算机视觉技术的重要研究内容,在智能安防、无人驾驶、场景识别、医疗诊断等多个领域得到广泛的应用。然而由于在现实应用场景中目标物体的尺度变化、遮挡程度以及外界环境变化等一些因素的影响,导致当前的目标检测算法都存在对于小尺度目标和存在部分遮挡目标的检测准确率较低的问题,因此,设计一种能够应对现实场景变化又能够准确地检测出目标的检测算法是当前目标检测算法的重点。本文在Faster R-CNN的基础上提出了一种基于特征融合与对抗遮挡网络的目标检测算法,通过引入特征融合网络和对抗遮挡网络来提升算法对小尺度目标和存在部分遮挡目标的检测性能。特征融合网络是利用逆卷积操作将高层特征图与低层特征图相融合,增加网络中低层特征图的提取能力,最终生成具有高分辨率高语义信息的单个高级特征图,并在其上进行预测,从而能更有效地检测出图像中的小目标。对抗遮挡网络在目标的深层特征图上创建遮挡,生成检测器难以判别的对抗性训练样本,同时检测器通过自身学习来对生成的具有遮挡的对抗性样本进行分类,这两者互相竞争又互相学习,从而使算法的检测性能进一步得到提升。此外、本文还通过改进的非极大值抑制算法来对预测框进行筛选,改善目标重叠或者目标被相邻目标部分遮挡时的检测结果。本文分别在PASCAL VOC、MS COCO和KITTI数据集上进行实验,大量的定量和定性实验结果表明本文所提出的算法达到了先进的检测精度。', '南京邮电大学', 3, '基于特征融合与对抗遮挡网络的目标检测算法设计与实现_周旺.caj', '/resource/document/基于特征融合与对抗遮挡网络的目标检测算法设计与实现_周旺.caj', '2017040330', 'https://w.buct.edu.cn/https/77726476706e69737468656265737421fbf952d2243e635930068cb8/kcms/detail/detail.aspx?dbcode=CMFD&dbname=CMFD202101&filename=1020427248.nh&v=uhvaJR1eGnv3F9VL88VbLbzfnE20JBQSzAqIb%25mmd2F6XWalczKCqLsf49nJXApQB6kLf', 1, 3, 1, 0, '2021-05-31 13:48:02', '2021-05-31 14:51:08');
INSERT INTO `t_document` VALUES (10, '面向自动驾驶场景的高效实时语义分割方法研究', '王雨', '2019-05-17', '图像语义分割（Image Semantic Segmentation）是计算机视觉领域中一项基础且极具挑战性的任务,其目标旨在估计图像中每个像素的类别标签,在驾驶辅助、室内室外场景解析和三维场景建模等视觉应用中发挥着日益重要的作用。最近,深度卷积神经网络（Deep Convolutional Neural Networks,DCNNs）已经成为解决图像语义分割的主流方法,通过使用大量标注数据（Ground Truth,GT）训练网络得到最佳拟合模型。现有方法通过堆叠大量的卷积层等方法构建复杂网络,尽管取得了显著的性能提升,但是面临严重的内存消耗和延迟等问题,使得其不适用于实时应用场景,如自动驾驶、增强现实、物联网等。因此,本文基于深度卷积神经网络,就兼具精度与速度的高效自动驾驶场景语义分割方法进行了深入研究,具体研究内容如下:第一,从轻量级模型设计的角度出发,本文提出了一种用于实时语义分割的轻量级编解码分割方法。该方法通过构建非对称的编码器-解码器网络以解决实时语义分割任务。编码器提出了一种新颖的基于分解卷积的残差编码模块,解码器设计了一个注意力金字塔模块,以提取密集的特征。实验结果表明,与目前最先进的深度卷积网络模型相比,该方法使用的参数量少,达到了71 FPS的前向推理速度以及70.6%的分割精度。该方法实现了分割精度和效率之间的平衡,成为解决图像语义分割任务的一种高效方法。第二,通过对图像语义分割这一稠密的结构性预测任务特性的分析,本文提出了一种面向实时语义分割的高效对称分割模型。该方法通过构建对称的编码器-解码器网络以解决实时图像语义分割任务。整个网络结构,通过对称的堆叠所提出的分解卷积单元和并行分解卷积单元以实现更快的模型前向推理速度,通过使用所提出的混合-带孔卷积模块扩大了感受野以提取深层次的语义特征,从而提高了网络的特征表达能力。实验结果表明,本文提出的整体网络架构在单个GTX 1080Ti GPU中能够以超过60 FPS的速度运行,达到平均交并比为70.7%的分割精度,且模型大小仅为1.6 M,成为在资源受限条件下实现高效图像语义分割的一种可行方法。第三,从上下文信息建模与利用人类视觉系统启发的注意力机制出发,本文提出了一种利用注意力机制引导的高效实时语义分割方法。该方法利用基于分解卷积改进的金字塔注意力模块提取密集的上下文信息。同时,该方法利用浅层特征图位置信息丰富的特点,基于空间注意力机制显式建模空间像素之间的语义关系来引导高层特征图进行上采样以恢复空间信息。大量的对比和消融实验结果表明,该方法能够以较快的前向推理速度运行且同时具有较高的分割精度。本文在两个城市场景基准数据集上对该方法进行了验证。在基准数据集Cityscapes上,该方法能够实现超过50帧每秒的前向推理速度,同时达到了71.3%的分割精度。在基准数据集Cam Vid上,该方法能够以超过90帧每秒的速度进行前向推理,同时达到了69.4%的分割精度。实验结果表明,本文提出的网络结构能够进行高效的图像语义分割,也可将其应用到更加复杂的场景理解任务中。综上,本文从轻量级模型设计、带孔卷积模块的利用、上下文信息建模、注意力机制的利用等角度出发,对基于深度卷积神经网络的高效图像语义分割方法进行了深入研究,并提出了面向自动驾驶场景的高效实时语义分割方法。实验结果表明,本文所提出的方法取得了具有竞争力的分割精度,同时能够有效地提高分割效率,具备了在真实场景中应用的能力。', '南京邮电大学', 4, '面向自动驾驶场景的高效实时语义分割方法研究_王雨.caj', '/resource/document/面向自动驾驶场景的高效实时语义分割方法研究_王雨.caj', '2017040330', 'https://w.buct.edu.cn/https/77726476706e69737468656265737421fbf952d2243e635930068cb8/kcms/detail/detail.aspx?dbcode=CMFD&dbname=CMFD202101&filename=1020427770.nh&v=w6IBw4hCDj1kuz3hfMB%25mmd2BiHhuYxwYdsqVRCrmxqfALdehipPyDiJuV07N17QdstIe', 1, 4, 1, 0, '2021-05-31 13:49:07', '2021-05-31 14:52:06');
INSERT INTO `t_document` VALUES (11, '基于机器学习的算法设计以及在智能系统中的应用', '田梦倩', '2020-05-14', '机器学习属于人工智能一个重要的分支,它涉及多个领域交叉学科,被广泛应用于各种智能系统,包括计算机视觉、无线通信、医学诊断、自然语言处理以及智能机器人等。本硕士论文主要研究先进机器学习算法并应用在计算机视觉和无线通信两个领域。在国家输电网中,对输电线路的检查是预防事故和停电的必要条件。而随着电力产业的发展,借助于监控摄像机的远程智能巡检逐渐替代成本高、风险高、效率低的人工巡检。为了节省成本,降低风险,本文设计了一种基于机器学习算法的高压传输线检测算法,主要是针对无人机以及固定低功耗摄像机拍摄的图片进行分析。该算法将待分析图片读入之后,通过诸如灰度处理,高斯模糊去噪,图像算术运算和灰度线性变换的操作来对图像进行预处理。其次,运用Canny算子检测出传输线的边缘像素点,并且设置感兴趣区域以提取传输线区域,抑制背景干扰。然后,分类检测出的边缘像素点,拟合传输线。最后,用红线绘制图像中的传输线,并且还输出已检测到的线的数量。与此同时,通过对比人工设定的传输线数量和算法输出的传输线数量,可以确定高压传输线的状态是否正常。实验证明,本文提出的算法可以去除天空和浮云等背景的干扰,同时可以准确检测高压线,且能够对交叉的高压线进行辨别,能处理边缘检测断点,具有一定的鲁棒性和实用性。针对第五代无线通信系统中的毫米波大规模多输入多输出问题,已经提出了许多混合预编码结构和方案。由于在该系统中射频链数量大大减少,传统的混合预编码技术会导致性能损失严重。本文主要针对该问题,首先介绍一种基于开关和逆变器的混合预编码架构,来作为降低功耗的解决方案。另外,通过对两阶混合预编码、基于天线选择的混合预编码方案和基于自适应交叉熵的混合预编码方案进行仿真,提供了对总速率和能量效率的详细性能分析,目的是为了证明基于ACE的方案的性能在所有参数值的有限范围内要远远优于其他方案。最后,确定了最佳的参数,并且证明了它们可以让系统达到最佳性能。', '南京邮电大学', 3, '基于机器学习的算法设计以及在智能系统中的应用_田梦倩.caj', '/resource/document/基于机器学习的算法设计以及在智能系统中的应用_田梦倩.caj', '2017040329', 'https://w.buct.edu.cn/https/77726476706e69737468656265737421fbf952d2243e635930068cb8/kcms/detail/detail.aspx?dbcode=CMFD&dbname=CMFD202101&filename=1020427788.nh&v=w6IBw4hCDj0qhjo0VRIZwNQDB3MWMDSQAdQF%25mmd2BcfpfQucfDqDBBQZDu3YHbnkU%25mmd2Fw9', 1, 4, 2, 0, '2021-05-31 14:06:33', '2021-05-31 15:18:45');
INSERT INTO `t_document` VALUES (12, '基于稠密反卷积聚合网络的图像语义分割技术研究', '卢竞男', '2021-04-30', '语义分割在机器人视觉中起着非常重要的作用,可用于众多现实世界的应用程序中,例如虚拟现实和增强现实,机器人技术和自动驾驶技术。最近,在全卷积网络（FCNs）中的不同层中探索多个特征图以捕获上下文信息已经引起了广泛的关注。本文针对语义分割任务,本文提出了两项工作:（1）针对FCN、U-net网络的不足,本文提出了一种新颖的编码器-解码器结构,称之为稠密反卷积聚合网络（Dense Deconvolution Aggregating Network,DDANet）。此网络通过将编码端深层的卷积特征稠密上采样到解码端来聚合上下文信息。融合在一起的特征图相互补充,使网络能更充分的探索包含在图像中的全局信息。DDANet通过端到端的训练,以匹配输入图像的分辨率。本文在自动驾驶数据集City Scapes上评估了提出的DDANet,实验结果显示,DDANet的方法优于之前提出的全卷积网络（FCNs）和编码器-解码器网络（EDNs）。在其他的数据集上,DDANet也取得了优异的成绩,例如PASCAL VOC 2012数据集和ISBI 2012数据集,分别是针对室内/室外场景解析和医学细胞分割的挑战。（2）本文继续对DDANet做了改进,提出了R-DDANet（Refine Dense Deconvolution Aggregating Network）。该网络整体上还是一种编码器-解码器结构。在编码端中,R-DDANet把之前的VGG-16换成ResNet-34。解码端部分新加入了MSC模块,编码端同层的特征图由直接复制到解码端改为先经过MSC模块再送入解码端,这样能够学习到编码端不同尺度的上下文信息。本文分别在Cityscapes和PASCAL VOC2012这两个数据集上对R-DDANet进行了评估。实验证明R-DDANet中的改进确实有效。', '南京邮电大学', 2, '基于稠密反卷积聚合网络的图像语义分割技术研究_卢竞男.caj', '/resource/document/基于稠密反卷积聚合网络的图像语义分割技术研究_卢竞男.caj', '2017040329', 'https://w.buct.edu.cn/https/77726476706e69737468656265737421fbf952d2243e635930068cb8/kcms/detail/detail.aspx?dbcode=CMFD&dbname=CMFD202101&filename=1020431497.nh&v=UT0f0po%25mmd2Bh12BF6XEV0Kzlk7bNz6GAODlJHhvx3clnimxr%25mmd2BDkK5TaFUkYBWuG29s5', 2, 3, 4, 0, '2021-05-31 14:12:30', '2021-05-31 15:16:19');
INSERT INTO `t_document` VALUES (13, '基于深度学习的垃圾分类算法研究', '陈伟', '2021-05-06', '垃圾分类是实现垃圾减量化、无害化和资源化的重要环节。传统垃圾分类大多使用人工分拣的方式进行,该方式存在着分拣效率低、人工成本高等弊端。随着我国智能化设备水平的不断提高,使用计算机视觉智能设备进行垃圾分拣成为可能。但是传统图像分类算法使用人工进行特征提取并分类,当垃圾种类与数量增多时,智能化设备的分类准确率与效率会有所下降。深度学习技术在计算机视觉领域的成功应用,提高了图像分类的准确率与效率,使得深度学习技术替代传统图像分类算法进行垃圾分类成为趋势。因此,使用深度学习技术对垃圾进行自动化分类具有重要意义。首先,本文构建垃圾分类数据集。数据集来源为:网络爬虫技术爬取生活中常见的各种垃圾图片,将不符合要求的垃圾图片剔除;使用华为垃圾数据集中部分垃圾图片,选择符合要求的垃圾图片数据;通过摄像设备收集生活中的常见垃圾图片。本文将垃圾图片分为四大类分别为:干垃圾、湿垃圾、有害垃圾和可回收物。其次,本文构建垃圾分类模型。比较各种经典图像分类模型的优缺点,设计实验选出适用于本课题的图像分类模型。同时,引入注意力机制对模型进行改进,研究注意力机制对Efficient Net系列网络准确率的影响。使用不同训练条件下模型进行集成学习,增加模型的泛化能力,减少单一模型可能产生的高方差问题。研究发现注意力机制对Efficient Net模型的准确率有提高作用,并且集成学习可以在一定程度上提高集成模型的准确率。最后,本文使用知识蒸馏技术将集成模型学习到的知识教给复杂度较低的MobileNet V2模型。实验结果表明,使用不同的知识蒸馏参数普遍会提高MobileNet V2模型的准确率。其中当温度T为20、α为0.7时,MobileNet模型的准确率提升最高,提升准确率为1.271%,最终Mobil Net V2模型的准确率达到93.494%。本文的研究成果有助于理解垃圾分类模型以及注意力机制、集成学习与知识蒸馏对垃圾图像分类模型的影响,为垃圾分类的实际应用提供理论指导。', '天津职业技术师范大学', 4, '基于深度学习的垃圾分类算法研究_陈伟.caj', '/resource/document/基于深度学习的垃圾分类算法研究_陈伟.caj', '2017040329', 'https://w.buct.edu.cn/https/77726476706e69737468656265737421fbf952d2243e635930068cb8/kcms/detail/detail.aspx?dbcode=CMFD&dbname=CMFDTEMP&filename=1021545375.nh&v=WpwNKI6ZCuWaUWe7zNjdQhLOIQKYCKUgCVTDgbiqa8k1b5vyhR5RTmLraLH2suol', 1, 4, 1, 0, '2021-05-31 14:50:20', '2021-05-31 14:51:31');

-- ----------------------------
-- Table structure for t_document_classify
-- ----------------------------
DROP TABLE IF EXISTS `t_document_classify`;
CREATE TABLE `t_document_classify`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `classify_id` bigint(0) NOT NULL COMMENT '类别id',
  `document_id` bigint(0) NOT NULL COMMENT '文献id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_document_classify
-- ----------------------------
INSERT INTO `t_document_classify` VALUES (1, 1, 3);
INSERT INTO `t_document_classify` VALUES (2, 2, 3);
INSERT INTO `t_document_classify` VALUES (3, 3, 3);
INSERT INTO `t_document_classify` VALUES (4, 2, 5);
INSERT INTO `t_document_classify` VALUES (5, 4, 5);
INSERT INTO `t_document_classify` VALUES (6, 5, 6);
INSERT INTO `t_document_classify` VALUES (7, 4, 7);
INSERT INTO `t_document_classify` VALUES (8, 2, 11);
INSERT INTO `t_document_classify` VALUES (9, 4, 11);
INSERT INTO `t_document_classify` VALUES (10, 2, 12);
INSERT INTO `t_document_classify` VALUES (11, 4, 12);
INSERT INTO `t_document_classify` VALUES (12, 2, 13);
INSERT INTO `t_document_classify` VALUES (13, 4, 13);

-- ----------------------------
-- Table structure for t_document_label
-- ----------------------------
DROP TABLE IF EXISTS `t_document_label`;
CREATE TABLE `t_document_label`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label_id` bigint(0) NOT NULL COMMENT '标签id',
  `document_id` bigint(0) NOT NULL COMMENT '文章id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_document_label
-- ----------------------------
INSERT INTO `t_document_label` VALUES (1, 1, 3);
INSERT INTO `t_document_label` VALUES (2, 2, 3);
INSERT INTO `t_document_label` VALUES (3, 3, 3);
INSERT INTO `t_document_label` VALUES (4, 2, 6);
INSERT INTO `t_document_label` VALUES (5, 2, 5);
INSERT INTO `t_document_label` VALUES (6, 9, 7);
INSERT INTO `t_document_label` VALUES (7, 9, 13);
INSERT INTO `t_document_label` VALUES (8, 2, 13);

-- ----------------------------
-- Table structure for t_file_md
-- ----------------------------
DROP TABLE IF EXISTS `t_file_md`;
CREATE TABLE `t_file_md`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文件存储路径',
  `file_md` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '该文件对应的md值',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_file_md
-- ----------------------------
INSERT INTO `t_file_md` VALUES (1, '/resource/document/最终版.docx', '6dfafc888855b1b701b9744fd563f524', '2021-04-21 16:37:53', NULL);
INSERT INTO `t_file_md` VALUES (2, '/resource/document/毕业设计（论文）任务书.doc', '42865590caaf281260b6474b85519eac', '2021-04-21 16:58:06', NULL);
INSERT INTO `t_file_md` VALUES (3, '/resource/document/2.2 程序使用安装说明书.docx', '92289a73c9e5aa10b9250b19d6982d56', '2021-04-21 17:21:02', NULL);
INSERT INTO `t_file_md` VALUES (4, '/resource/document/3 系统使用说明书.docx', 'e8b843b31f3dbc1d280e74d181ac19c6', '2021-04-21 20:59:38', NULL);
INSERT INTO `t_file_md` VALUES (5, '/resource/document/编译原理课程设计报告1.docx', 'a7ccda5a301f267cdd287394748af9a4', '2021-04-21 21:09:35', NULL);
INSERT INTO `t_file_md` VALUES (6, '/resource/document/Redis的主从复制.md', '95d24dae15717a7409d01bb459ce03d5', '2021-04-29 17:08:18', NULL);
INSERT INTO `t_file_md` VALUES (7, '/resource/document/Redis序列化方式.md', '5c8e3d862d1439045d57ad87f78ab45a', '2021-04-29 17:10:39', NULL);
INSERT INTO `t_file_md` VALUES (15, '/resource/class/张俊峰(2020-2021-2)课表.pdf', '31646892bf9dd8a7aaab4e0dcf1c16cf', '2021-05-13 21:02:03', NULL);
INSERT INTO `t_file_md` VALUES (16, '/resource/class/张俊峰(2019-2020-2)课表.pdf', '1de6a2a9be50ad22ed9b624df9491a2f', '2021-05-13 21:03:07', NULL);
INSERT INTO `t_file_md` VALUES (17, '/resource/document/基于SSM的权限管理系统及数据可视化_张卫华.caj', 'd24386a3bbcfa91bc6581cb17a45bdcb', '2021-05-20 13:59:54', NULL);
INSERT INTO `t_file_md` VALUES (18, '/resource/document/基于前后端分离技术的web开发框架设计_吴昌政.caj', 'cb57e41962ee78b0a396d3728c88a5eb', '2021-05-20 14:59:02', NULL);
INSERT INTO `t_file_md` VALUES (19, '/resource/document/单幅图像去雾算法的研究_王雪梅.caj', '929a27e90cebb224821e97d448f70fe5', '2021-05-31 13:43:51', NULL);
INSERT INTO `t_file_md` VALUES (20, '/resource/document/基于特征融合与对抗遮挡网络的目标检测算法设计与实现_周旺.caj', 'ba48cb950965806eeb3af22e4833c1da', '2021-05-31 13:48:18', NULL);
INSERT INTO `t_file_md` VALUES (21, '/resource/document/面向自动驾驶场景的高效实时语义分割方法研究_王雨.caj', 'd16eaec21099e11b75237ccd8bb37404', '2021-05-31 13:49:21', NULL);
INSERT INTO `t_file_md` VALUES (22, '/resource/document/基于机器学习的算法设计以及在智能系统中的应用_田梦倩.caj', '7f219e63a5a41d80c4a9d2d88a718b26', '2021-05-31 14:09:15', NULL);
INSERT INTO `t_file_md` VALUES (23, '/resource/document/基于稠密反卷积聚合网络的图像语义分割技术研究_卢竞男.caj', 'f6923f22f22e6e41f525364bc9852b94', '2021-05-31 14:12:52', NULL);
INSERT INTO `t_file_md` VALUES (24, '/resource/document/基于深度学习的垃圾分类算法研究_陈伟.caj', '776b509b1f2e8669e2768f3c1893945a', '2021-05-31 14:50:54', NULL);

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '团队名称',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '团队介绍',
  `work_direction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '工作内容或方向',
  `by_user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建者id 也是负责人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES (1, '2017级计科1703', '计算机系1703班团队', '团队管理系统方向，知识图谱', '2017040330', '2021-05-11 19:04:07', '2021-05-11 22:38:04');
INSERT INTO `t_group` VALUES (2, '张俊峰团队', '张俊峰为主力开发', '主要工作：团队资源管理系统的实现', '2017040330', '2021-05-11 19:13:37', '2021-05-11 22:38:13');

-- ----------------------------
-- Table structure for t_group_member
-- ----------------------------
DROP TABLE IF EXISTS `t_group_member`;
CREATE TABLE `t_group_member`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(0) NOT NULL COMMENT '团队id',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '成员id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `group_user_unique_index`(`group_id`, `user_id`) USING BTREE COMMENT '团队里成员id唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_group_member
-- ----------------------------
INSERT INTO `t_group_member` VALUES (23, 1, '2017040328');
INSERT INTO `t_group_member` VALUES (22, 1, '2017040329');
INSERT INTO `t_group_member` VALUES (21, 1, '2017040330');
INSERT INTO `t_group_member` VALUES (7, 2, '2017040328');
INSERT INTO `t_group_member` VALUES (24, 2, '2017040329');
INSERT INTO `t_group_member` VALUES (1, 2, '2017040330');
INSERT INTO `t_group_member` VALUES (25, 2, '2017040334');

-- ----------------------------
-- Table structure for t_label
-- ----------------------------
DROP TABLE IF EXISTS `t_label`;
CREATE TABLE `t_label`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标签名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标签描述',
  `by_user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建者',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_label
-- ----------------------------
INSERT INTO `t_label` VALUES (1, '博客', NULL, '2017040329', '2021-05-02 14:35:11', '2021-05-23 14:09:09');
INSERT INTO `t_label` VALUES (2, '技术', NULL, '2017040328', '2021-05-02 22:07:32', '2021-05-23 14:09:14');
INSERT INTO `t_label` VALUES (3, '翻译好', NULL, '2017040330', '2021-05-02 22:07:42', '2021-05-23 14:09:18');
INSERT INTO `t_label` VALUES (9, '计算机视觉', NULL, '2017040330', '2021-05-31 13:44:18', NULL);
INSERT INTO `t_label` VALUES (10, '水稻技术', NULL, '2017040329', '2021-05-31 14:35:30', NULL);

-- ----------------------------
-- Table structure for t_like
-- ----------------------------
DROP TABLE IF EXISTS `t_like`;
CREATE TABLE `t_like`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `document_id` bigint(0) NOT NULL COMMENT '文献id',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '点赞者id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `document_user_unique_index`(`document_id`, `user_id`) USING BTREE COMMENT '文献用户唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_like
-- ----------------------------
INSERT INTO `t_like` VALUES (14, 2, '2017040330', '2021-05-09 17:20:50', NULL);
INSERT INTO `t_like` VALUES (15, 3, '2017040330', '2021-05-11 11:30:34', NULL);
INSERT INTO `t_like` VALUES (16, 5, '2017040330', '2021-05-20 14:46:26', NULL);
INSERT INTO `t_like` VALUES (17, 6, '2017040330', '2021-05-20 14:59:39', NULL);
INSERT INTO `t_like` VALUES (18, 9, '2017040329', '2021-05-31 14:51:06', NULL);
INSERT INTO `t_like` VALUES (19, 13, '2017040329', '2021-05-31 14:51:29', NULL);
INSERT INTO `t_like` VALUES (20, 11, '2017040329', '2021-05-31 14:51:48', NULL);
INSERT INTO `t_like` VALUES (21, 10, '2017040329', '2021-05-31 14:52:06', NULL);
INSERT INTO `t_like` VALUES (22, 7, '2017040329', '2021-05-31 14:52:19', NULL);
INSERT INTO `t_like` VALUES (23, 12, '2017040329', '2021-05-31 14:52:30', NULL);
INSERT INTO `t_like` VALUES (24, 12, '2017040330', '2021-05-31 14:54:57', NULL);

-- ----------------------------
-- Table structure for t_meeting
-- ----------------------------
DROP TABLE IF EXISTS `t_meeting`;
CREATE TABLE `t_meeting`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `organizer_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '组织者id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '组会内容',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '会议地点',
  `state` tinyint(0) NOT NULL COMMENT '会议状态:0: 未开始,1: 进行中,2: 已结束',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_meeting
-- ----------------------------
INSERT INTO `t_meeting` VALUES (5, '2017040330', '讨论毕业设计完成进度', '2021-05-14 15:50:59', '2021-05-14 19:00:00', '信息学院三楼', 2, '2021-05-19 11:21:13', '2021-05-31 13:52:17');
INSERT INTO `t_meeting` VALUES (6, '2017040330', '讨论毕设设计的详细事宜', '2021-03-10 00:00:00', '2021-05-20 16:29:03', '教学楼302', 0, '2021-05-20 16:30:06', NULL);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(0) NOT NULL COMMENT '父级菜单id',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '菜单路径',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单图标',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限标识(sys:user:add,sys:edit:edit)',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单类型(M目录 C菜单 F按钮)',
  `visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '菜单状态(0隐藏 1显示)',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '首页', 0, '/dashboard/home', 'home', 'system:achieve:list', 'C', 1, '2021-05-22 14:53:15', NULL);
INSERT INTO `t_menu` VALUES (2, '用户管理', 0, '/dashboard/user', 'user_manage', 'system:user:list', 'C', 1, '2021-04-09 15:31:18', '2021-05-14 23:01:44');
INSERT INTO `t_menu` VALUES (3, '角色管理', 0, '/dashboard/role', 'user_role', 'system:role:list', 'C', 1, '2021-05-16 15:07:06', '2021-05-16 15:09:33');
INSERT INTO `t_menu` VALUES (4, '团队管理', 0, 'team_manage', 'group', '', 'M', 1, '2021-04-09 15:39:13', '2021-05-14 23:05:10');
INSERT INTO `t_menu` VALUES (5, '团队管理', 4, '/dashboard/team/manage', 'group', 'system:team:list', 'C', 1, '2021-05-11 16:12:19', '2021-05-14 23:06:42');
INSERT INTO `t_menu` VALUES (6, '团队展示', 4, '/dashboard/team/show', 'group', 'system:team:list', 'C', 1, '2021-05-11 16:13:45', '2021-05-14 23:06:44');
INSERT INTO `t_menu` VALUES (7, '文献管理', 0, 'document_manage', 'document', '', 'M', 1, '2021-04-27 19:12:04', '2021-05-14 23:05:54');
INSERT INTO `t_menu` VALUES (8, '文献管理', 7, '/dashboard/document/manage', 'document', 'system:document:list', 'C', 1, '2021-04-09 15:38:26', '2021-05-14 23:04:44');
INSERT INTO `t_menu` VALUES (9, '文献展示', 7, '/dashboard/document/detail', 'document', 'system:document:list', 'C', 1, '2021-04-29 18:21:09', '2021-05-14 23:06:08');
INSERT INTO `t_menu` VALUES (10, '类别管理', 7, '/dashboard/document/class', 'document', 'system:classify:list', 'C', 1, '2021-05-23 13:12:21', '2021-05-23 13:45:01');
INSERT INTO `t_menu` VALUES (11, '标签管理', 7, '/dashboard/document/label', 'document', 'system:label:list', 'C', 1, '2021-05-23 13:44:48', NULL);
INSERT INTO `t_menu` VALUES (12, '任务清单', 0, '/dashboard/task', 'task', 'system:task:list', 'C', 1, '2021-04-09 15:43:18', '2021-05-14 23:05:49');
INSERT INTO `t_menu` VALUES (13, '会议信息', 0, '/dashboard/meeting', 'meeting', 'system:meeting:list', 'C', 1, '2021-04-09 15:42:53', '2021-05-14 23:05:43');
INSERT INTO `t_menu` VALUES (14, '周报日报', 0, '/dashboard/report', 'weekly', 'system:report:list', 'C', 1, '2021-04-09 15:40:49', '2021-05-14 23:05:20');
INSERT INTO `t_menu` VALUES (15, '软件管理', 0, 'software_manage', 'software', 'system:software:list', 'M', 1, '2021-05-11 15:42:30', '2021-05-14 23:06:22');
INSERT INTO `t_menu` VALUES (16, '软件管理', 15, '/dashboard/software/manage', 'software', 'system:software:list', 'C', 1, '2021-04-09 15:42:02', '2021-05-14 23:05:29');
INSERT INTO `t_menu` VALUES (17, '软件展示', 15, '/dashboard/software/detail', 'software', 'system:software:list', 'C', 1, '2021-05-11 15:43:35', '2021-05-14 23:06:34');
INSERT INTO `t_menu` VALUES (18, '个人成果', 0, 'achievement', 'achievement', 'system:achieve:list', 'C', 1, '2021-04-09 15:36:48', '2021-05-15 22:55:59');
INSERT INTO `t_menu` VALUES (19, '成果管理', 18, '/dashboard/achievement', 'achievement', 'system:achieve:list', 'C', 1, '2021-05-15 22:55:51', NULL);
INSERT INTO `t_menu` VALUES (20, '成果展示', 18, '/dashboard/achievement/show', 'achievement', 'system:achieve:list', 'C', 1, '2021-05-15 22:56:44', NULL);
INSERT INTO `t_menu` VALUES (21, '资产管理', 0, '/dashboard/assets', 'assets', 'system:assets:list', 'C', 1, '2021-04-09 15:37:12', '2021-05-14 23:04:36');
INSERT INTO `t_menu` VALUES (22, '课表信息', 0, '/dashboard/class', 'class', 'system:class:list', 'C', 1, '2021-05-12 22:53:24', '2021-05-14 23:06:50');
INSERT INTO `t_menu` VALUES (23, '系统日志', 0, '/dashboard/systemlog', 'document', 'system:log:list', 'C', 1, '2021-05-23 17:04:33', NULL);

-- ----------------------------
-- Table structure for t_report
-- ----------------------------
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `type` tinyint(0) NOT NULL COMMENT '类型：0：周报，1：日报',
  `finished_task` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '已完成任务',
  `unfinished_task` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '未完成任务',
  `plan_task` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '计划任务',
  `self_review` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '自我评价',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '发布者id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_report
-- ----------------------------
INSERT INTO `t_report` VALUES (1, 1, '完成任务', '未完成任务', '计划任务', '感觉良好', '张俊峰', '2017040330', '2021-05-14 16:16:52');
INSERT INTO `t_report` VALUES (2, 1, '完成日报和周报顽疾', '权限功能', '完成权限功能', '时间优点紧张', '张俊峰', '2017040330', '2021-05-14 16:30:38');
INSERT INTO `t_report` VALUES (10, 0, '完成菜单功能的编码工作', '1.菜单权限的功能还未完成\n2.还未进行代码调试', '1.下周主要完成菜单功能\n2.可以开始资产功能的管理', '这周任务安排很满', '张俊峰', '2017040330', '2021-05-20 17:14:45');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色字符串',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', 'admin', '2021-05-14 21:33:47', NULL);
INSERT INTO `t_role` VALUES (2, '普通用户', 'common', '2021-05-14 21:43:30', '2021-05-14 22:55:35');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(0) NOT NULL COMMENT '角色id',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单权限id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `permission_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 1, 1);
INSERT INTO `t_role_menu` VALUES (2, 1, 2);
INSERT INTO `t_role_menu` VALUES (3, 1, 3);
INSERT INTO `t_role_menu` VALUES (4, 1, 4);
INSERT INTO `t_role_menu` VALUES (5, 1, 5);
INSERT INTO `t_role_menu` VALUES (6, 1, 6);
INSERT INTO `t_role_menu` VALUES (7, 1, 7);
INSERT INTO `t_role_menu` VALUES (8, 1, 8);
INSERT INTO `t_role_menu` VALUES (9, 1, 9);
INSERT INTO `t_role_menu` VALUES (10, 1, 10);
INSERT INTO `t_role_menu` VALUES (11, 1, 11);
INSERT INTO `t_role_menu` VALUES (12, 1, 12);
INSERT INTO `t_role_menu` VALUES (13, 1, 13);
INSERT INTO `t_role_menu` VALUES (14, 1, 14);
INSERT INTO `t_role_menu` VALUES (15, 1, 15);
INSERT INTO `t_role_menu` VALUES (16, 1, 16);
INSERT INTO `t_role_menu` VALUES (17, 1, 17);
INSERT INTO `t_role_menu` VALUES (18, 1, 18);
INSERT INTO `t_role_menu` VALUES (19, 1, 19);
INSERT INTO `t_role_menu` VALUES (20, 1, 20);
INSERT INTO `t_role_menu` VALUES (21, 1, 21);
INSERT INTO `t_role_menu` VALUES (22, 1, 22);
INSERT INTO `t_role_menu` VALUES (23, 1, 23);
INSERT INTO `t_role_menu` VALUES (24, 2, 1);
INSERT INTO `t_role_menu` VALUES (25, 2, 4);
INSERT INTO `t_role_menu` VALUES (26, 2, 5);
INSERT INTO `t_role_menu` VALUES (27, 2, 6);
INSERT INTO `t_role_menu` VALUES (28, 2, 7);
INSERT INTO `t_role_menu` VALUES (29, 2, 8);
INSERT INTO `t_role_menu` VALUES (30, 2, 9);
INSERT INTO `t_role_menu` VALUES (31, 2, 10);
INSERT INTO `t_role_menu` VALUES (32, 2, 11);
INSERT INTO `t_role_menu` VALUES (33, 2, 12);
INSERT INTO `t_role_menu` VALUES (34, 2, 13);
INSERT INTO `t_role_menu` VALUES (35, 2, 14);
INSERT INTO `t_role_menu` VALUES (36, 2, 15);
INSERT INTO `t_role_menu` VALUES (37, 2, 16);
INSERT INTO `t_role_menu` VALUES (38, 2, 17);
INSERT INTO `t_role_menu` VALUES (39, 2, 18);
INSERT INTO `t_role_menu` VALUES (40, 2, 19);
INSERT INTO `t_role_menu` VALUES (41, 2, 20);
INSERT INTO `t_role_menu` VALUES (42, 2, 21);
INSERT INTO `t_role_menu` VALUES (43, 2, 22);

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `document_id` bigint(0) NOT NULL COMMENT '文献id',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '评分者id',
  `score` tinyint(0) NOT NULL COMMENT '分数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_document_unique`(`document_id`, `user_id`) USING BTREE COMMENT '每个用户只能对每个文献评论一次'
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES (3, 2, '2017040330', 4, '2021-05-04 22:39:16', NULL);
INSERT INTO `t_score` VALUES (4, 2, '2017040331', 3, '2021-05-04 22:40:14', NULL);
INSERT INTO `t_score` VALUES (7, 4, '2017040330', 3, '2021-05-05 17:06:17', NULL);
INSERT INTO `t_score` VALUES (8, 3, '2017040330', 3, '2021-05-05 21:48:38', NULL);
INSERT INTO `t_score` VALUES (9, 1, '2017040330', 3, '2021-05-07 17:12:19', NULL);
INSERT INTO `t_score` VALUES (10, 3, '2017040329', 3, '2021-05-15 20:39:15', NULL);
INSERT INTO `t_score` VALUES (11, 5, '2017040330', 5, '2021-05-20 14:46:23', NULL);
INSERT INTO `t_score` VALUES (12, 6, '2017040330', 5, '2021-05-20 14:59:37', NULL);
INSERT INTO `t_score` VALUES (13, 9, '2017040329', 3, '2021-05-31 14:51:08', NULL);
INSERT INTO `t_score` VALUES (14, 13, '2017040329', 4, '2021-05-31 14:51:31', NULL);
INSERT INTO `t_score` VALUES (15, 11, '2017040329', 4, '2021-05-31 14:51:46', NULL);
INSERT INTO `t_score` VALUES (16, 10, '2017040329', 4, '2021-05-31 14:52:04', NULL);
INSERT INTO `t_score` VALUES (17, 7, '2017040329', 4, '2021-05-31 14:52:18', NULL);
INSERT INTO `t_score` VALUES (18, 12, '2017040329', 4, '2021-05-31 14:52:29', NULL);
INSERT INTO `t_score` VALUES (19, 12, '2017040330', 2, '2021-05-31 14:55:03', NULL);

-- ----------------------------
-- Table structure for t_software
-- ----------------------------
DROP TABLE IF EXISTS `t_software`;
CREATE TABLE `t_software`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '软件名',
  `version` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '软件版本号',
  `download_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '下载地址',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '软件备注',
  `upload_user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '上传用户',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_software
-- ----------------------------
INSERT INTO `t_software` VALUES (1, 'Bandizip', '7.16', 'https://cn.bandisoft.com/bandizip/', '免费好用的解压缩软件，无广告', '2017040329', '2021-05-08 19:52:01', '2021-05-15 21:20:56');
INSERT INTO `t_software` VALUES (2, 'Typora', NULL, 'https://typora.io/#download', '客户端markdown软件，支持mac和windows，无广告，可配合PicGo上传图片到图床', '2017040330', '2021-05-09 15:54:46', '2021-05-15 21:21:01');

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '请求url',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求描述',
  `request_date` datetime(0) NULL DEFAULT NULL COMMENT '请求时间',
  `request_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求方式',
  `request_body` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求体',
  `response_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '响应状态值',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 552 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
INSERT INTO `t_sys_log` VALUES (1, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-23 16:33:56', 'GET', NULL, '200', '2017040329', '2021-05-23 16:33:55', NULL);
INSERT INTO `t_sys_log` VALUES (2, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-23 16:41:48', 'GET', NULL, '200', '2017040329', '2021-05-23 16:41:48', NULL);
INSERT INTO `t_sys_log` VALUES (3, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-23 16:41:49', 'GET', NULL, '200', '2017040329', '2021-05-23 16:41:48', NULL);
INSERT INTO `t_sys_log` VALUES (4, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-23 16:41:52', 'GET', NULL, '200', '2017040329', '2021-05-23 16:41:51', NULL);
INSERT INTO `t_sys_log` VALUES (5, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-23 16:41:52', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-23 16:41:52', NULL);
INSERT INTO `t_sys_log` VALUES (6, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-23 16:41:58', 'GET', NULL, '200', '2017040329', '2021-05-23 16:41:57', NULL);
INSERT INTO `t_sys_log` VALUES (7, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-23 16:41:58', 'GET', NULL, '200', '2017040329', '2021-05-23 16:41:58', NULL);
INSERT INTO `t_sys_log` VALUES (8, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-23 16:42:00', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-23 16:42:00', NULL);
INSERT INTO `t_sys_log` VALUES (9, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-23 16:42:00', 'GET', NULL, '200', '2017040329', '2021-05-23 16:42:00', NULL);
INSERT INTO `t_sys_log` VALUES (10, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 16:58:20', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 16:58:19', NULL);
INSERT INTO `t_sys_log` VALUES (11, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-23 16:58:25', 'GET', NULL, '200', '2017040330', '2021-05-23 16:58:25', NULL);
INSERT INTO `t_sys_log` VALUES (12, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-05-23 17:01:28', 'GET', NULL, '200', '2017040330', '2021-05-23 17:01:27', NULL);
INSERT INTO `t_sys_log` VALUES (13, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-05-23 17:01:31', 'GET', NULL, '200', '2017040330', '2021-05-23 17:01:30', NULL);
INSERT INTO `t_sys_log` VALUES (14, 'http://localhost:8888/manage/class', '获取所有课表', '2021-05-23 17:01:32', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-23 17:01:32', NULL);
INSERT INTO `t_sys_log` VALUES (15, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-23 17:01:36', 'GET', NULL, '200', '2017040330', '2021-05-23 17:01:36', NULL);
INSERT INTO `t_sys_log` VALUES (16, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-23 17:01:37', 'GET', NULL, '200', '2017040330', '2021-05-23 17:01:37', NULL);
INSERT INTO `t_sys_log` VALUES (17, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-23 17:01:39', 'GET', NULL, '200', '2017040330', '2021-05-23 17:01:38', NULL);
INSERT INTO `t_sys_log` VALUES (18, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-23 17:01:39', 'GET', NULL, '200', '2017040330', '2021-05-23 17:01:38', NULL);
INSERT INTO `t_sys_log` VALUES (19, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-23 17:01:39', 'GET', NULL, '200', '2017040330', '2021-05-23 17:01:39', NULL);
INSERT INTO `t_sys_log` VALUES (20, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-23 17:06:42', 'GET', NULL, '200', '2017040330', '2021-05-23 17:06:42', NULL);
INSERT INTO `t_sys_log` VALUES (21, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:06:42', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:06:42', NULL);
INSERT INTO `t_sys_log` VALUES (22, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-23 17:06:43', 'GET', NULL, '200', '2017040330', '2021-05-23 17:06:42', NULL);
INSERT INTO `t_sys_log` VALUES (23, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-23 17:06:43', 'GET', NULL, '200', '2017040330', '2021-05-23 17:06:42', NULL);
INSERT INTO `t_sys_log` VALUES (24, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:11:37', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:11:36', NULL);
INSERT INTO `t_sys_log` VALUES (25, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:12:44', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:12:43', NULL);
INSERT INTO `t_sys_log` VALUES (26, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:12:55', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:12:54', NULL);
INSERT INTO `t_sys_log` VALUES (27, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:13:12', 'GET', NULL, '200', '2017040330', '2021-05-23 17:13:12', NULL);
INSERT INTO `t_sys_log` VALUES (28, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:15:14', 'GET', NULL, '200', '2017040330', '2021-05-23 17:15:13', NULL);
INSERT INTO `t_sys_log` VALUES (29, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:15:14', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:15:13', NULL);
INSERT INTO `t_sys_log` VALUES (30, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:15:28', 'GET', NULL, '200', '2017040330', '2021-05-23 17:15:27', NULL);
INSERT INTO `t_sys_log` VALUES (31, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-23 17:18:56', 'GET', NULL, '200', '2017040330', '2021-05-23 17:18:55', NULL);
INSERT INTO `t_sys_log` VALUES (32, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-23 17:18:56', 'GET', NULL, '200', '2017040330', '2021-05-23 17:18:55', NULL);
INSERT INTO `t_sys_log` VALUES (33, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-23 17:18:56', 'GET', NULL, '200', '2017040330', '2021-05-23 17:18:55', NULL);
INSERT INTO `t_sys_log` VALUES (34, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:19:03', 'GET', NULL, '200', '2017040330', '2021-05-23 17:19:02', NULL);
INSERT INTO `t_sys_log` VALUES (35, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:22:30', 'GET', NULL, '200', '2017040330', '2021-05-23 17:22:30', NULL);
INSERT INTO `t_sys_log` VALUES (36, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:22:34', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:22:33', NULL);
INSERT INTO `t_sys_log` VALUES (37, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:22:34', 'GET', NULL, '200', '2017040330', '2021-05-23 17:22:33', NULL);
INSERT INTO `t_sys_log` VALUES (38, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:23:32', 'GET', NULL, '200', '2017040330', '2021-05-23 17:23:31', NULL);
INSERT INTO `t_sys_log` VALUES (39, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:23:34', 'GET', NULL, '200', '2017040330', '2021-05-23 17:23:34', NULL);
INSERT INTO `t_sys_log` VALUES (40, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:23:35', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:23:34', NULL);
INSERT INTO `t_sys_log` VALUES (41, 'http://localhost:8888/manage/systemlogs/byUserId', '通过用户查找', '2021-05-23 17:23:44', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-23 17:23:43', NULL);
INSERT INTO `t_sys_log` VALUES (42, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:23:51', 'GET', NULL, '200', '2017040330', '2021-05-23 17:23:51', NULL);
INSERT INTO `t_sys_log` VALUES (43, 'http://localhost:8888/manage/systemlogs/byUserId', '通过用户查找', '2021-05-23 17:23:55', 'GET', 'userId=2017040329', '200', '2017040330', '2021-05-23 17:23:55', NULL);
INSERT INTO `t_sys_log` VALUES (44, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:26:13', 'GET', NULL, '200', '2017040330', '2021-05-23 17:26:13', NULL);
INSERT INTO `t_sys_log` VALUES (45, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:26:13', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:26:13', NULL);
INSERT INTO `t_sys_log` VALUES (46, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:29:32', 'GET', NULL, '200', '2017040330', '2021-05-23 17:29:31', NULL);
INSERT INTO `t_sys_log` VALUES (47, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:29:40', 'GET', NULL, '200', '2017040330', '2021-05-23 17:29:39', NULL);
INSERT INTO `t_sys_log` VALUES (48, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:29:40', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:29:40', NULL);
INSERT INTO `t_sys_log` VALUES (49, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:30:55', 'GET', NULL, '200', '2017040330', '2021-05-23 17:30:55', NULL);
INSERT INTO `t_sys_log` VALUES (50, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:30:55', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:30:55', NULL);
INSERT INTO `t_sys_log` VALUES (51, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-23 17:31:35', 'GET', 'role=admin', '200', '2017040330', '2021-05-23 17:31:35', NULL);
INSERT INTO `t_sys_log` VALUES (52, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-23 17:31:35', 'GET', NULL, '200', '2017040330', '2021-05-23 17:31:35', NULL);
INSERT INTO `t_sys_log` VALUES (53, 'http://localhost:8888/manage/systemlogs/byTime', '通过时间查找日志', '2021-05-23 17:31:56', 'GET', 'time=2021-05-23', '200', '2017040330', '2021-05-23 17:31:56', NULL);
INSERT INTO `t_sys_log` VALUES (54, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-24 16:19:04', 'GET', NULL, '200', '2017040330', '2021-05-24 16:19:04', NULL);
INSERT INTO `t_sys_log` VALUES (55, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-24 16:19:05', 'GET', 'role=admin', '200', '2017040330', '2021-05-24 16:19:04', NULL);
INSERT INTO `t_sys_log` VALUES (56, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-24 16:19:14', 'GET', NULL, '200', '2017040330', '2021-05-24 16:19:14', NULL);
INSERT INTO `t_sys_log` VALUES (57, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-24 16:19:14', 'GET', NULL, '200', '2017040330', '2021-05-24 16:19:14', NULL);
INSERT INTO `t_sys_log` VALUES (58, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-24 16:19:15', 'GET', NULL, '200', '2017040330', '2021-05-24 16:19:14', NULL);
INSERT INTO `t_sys_log` VALUES (59, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-24 16:20:41', 'GET', NULL, '200', '2017040330', '2021-05-24 16:20:40', NULL);
INSERT INTO `t_sys_log` VALUES (60, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-24 16:20:41', 'GET', NULL, '200', '2017040330', '2021-05-24 16:20:40', NULL);
INSERT INTO `t_sys_log` VALUES (61, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-24 16:20:41', 'GET', NULL, '200', '2017040330', '2021-05-24 16:20:41', NULL);
INSERT INTO `t_sys_log` VALUES (62, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-24 16:20:47', 'GET', 'id=5&type=1', '200', '2017040330', '2021-05-24 16:20:47', NULL);
INSERT INTO `t_sys_log` VALUES (63, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-24 16:20:49', 'GET', 'id=5&userId=2017040330', '200', '2017040330', '2021-05-24 16:20:49', NULL);
INSERT INTO `t_sys_log` VALUES (64, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-24 16:24:15', 'GET', 'role=admin', '200', '2017040330', '2021-05-24 16:24:15', NULL);
INSERT INTO `t_sys_log` VALUES (65, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-24 16:24:16', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-24 16:24:16', NULL);
INSERT INTO `t_sys_log` VALUES (66, 'http://localhost:8888/manage/resetPassword', '重置用户密码', '2021-05-25 21:24:53', 'POST', NULL, '200', '2017040330', '2021-05-25 21:24:53', NULL);
INSERT INTO `t_sys_log` VALUES (67, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-25 21:25:13', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-25 21:25:12', NULL);
INSERT INTO `t_sys_log` VALUES (68, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-25 21:25:14', 'GET', 'role=admin', '200', '2017040330', '2021-05-25 21:25:13', NULL);
INSERT INTO `t_sys_log` VALUES (69, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-25 21:25:22', 'GET', NULL, '200', '2017040330', '2021-05-25 21:25:21', NULL);
INSERT INTO `t_sys_log` VALUES (70, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-25 21:25:22', 'GET', NULL, '200', '2017040330', '2021-05-25 21:25:22', NULL);
INSERT INTO `t_sys_log` VALUES (71, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-25 21:25:23', 'GET', NULL, '200', '2017040330', '2021-05-25 21:25:23', NULL);
INSERT INTO `t_sys_log` VALUES (72, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-25 21:25:25', 'GET', 'id=5&type=1', '200', '2017040330', '2021-05-25 21:25:24', NULL);
INSERT INTO `t_sys_log` VALUES (73, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-25 21:25:25', 'GET', 'id=5&userId=2017040330', '200', '2017040330', '2021-05-25 21:25:25', NULL);
INSERT INTO `t_sys_log` VALUES (74, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-25 21:25:37', 'GET', NULL, '200', '2017040330', '2021-05-25 21:25:36', NULL);
INSERT INTO `t_sys_log` VALUES (75, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-25 21:25:38', 'GET', NULL, '200', '2017040330', '2021-05-25 21:25:37', NULL);
INSERT INTO `t_sys_log` VALUES (76, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-25 21:25:38', 'GET', NULL, '200', '2017040330', '2021-05-25 21:25:38', NULL);
INSERT INTO `t_sys_log` VALUES (77, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-25 21:28:38', 'GET', NULL, '200', '2017040330', '2021-05-25 21:28:38', NULL);
INSERT INTO `t_sys_log` VALUES (78, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-25 21:28:38', 'GET', NULL, '200', '2017040330', '2021-05-25 21:28:37', NULL);
INSERT INTO `t_sys_log` VALUES (79, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-25 21:28:38', 'GET', NULL, '200', '2017040330', '2021-05-25 21:28:38', NULL);
INSERT INTO `t_sys_log` VALUES (80, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-25 21:28:44', 'GET', 'id=6&type=1', '200', '2017040330', '2021-05-25 21:28:43', NULL);
INSERT INTO `t_sys_log` VALUES (81, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-25 21:28:45', 'GET', 'id=6&userId=2017040330', '200', '2017040330', '2021-05-25 21:28:44', NULL);
INSERT INTO `t_sys_log` VALUES (82, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-25 21:28:48', 'GET', NULL, '200', '2017040330', '2021-05-25 21:28:47', NULL);
INSERT INTO `t_sys_log` VALUES (83, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-25 21:28:48', 'GET', NULL, '200', '2017040330', '2021-05-25 21:28:47', NULL);
INSERT INTO `t_sys_log` VALUES (84, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-25 21:28:48', 'GET', NULL, '200', '2017040330', '2021-05-25 21:28:47', NULL);
INSERT INTO `t_sys_log` VALUES (85, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-25 21:29:37', 'GET', 'id=6&type=1', '200', '2017040330', '2021-05-25 21:29:36', NULL);
INSERT INTO `t_sys_log` VALUES (86, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-25 21:29:37', 'GET', 'id=6&userId=2017040330', '200', '2017040330', '2021-05-25 21:29:37', NULL);
INSERT INTO `t_sys_log` VALUES (87, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-25 21:29:49', 'GET', NULL, '200', '2017040330', '2021-05-25 21:29:48', NULL);
INSERT INTO `t_sys_log` VALUES (88, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-25 21:29:49', 'GET', NULL, '200', '2017040330', '2021-05-25 21:29:48', NULL);
INSERT INTO `t_sys_log` VALUES (89, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-25 21:29:49', 'GET', NULL, '200', '2017040330', '2021-05-25 21:29:48', NULL);
INSERT INTO `t_sys_log` VALUES (90, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-25 21:29:51', 'GET', 'id=6&userId=2017040330', '200', '2017040330', '2021-05-25 21:29:50', NULL);
INSERT INTO `t_sys_log` VALUES (91, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-25 21:29:51', 'GET', 'id=6&type=1', '200', '2017040330', '2021-05-25 21:29:51', NULL);
INSERT INTO `t_sys_log` VALUES (92, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-05-25 21:30:05', 'GET', NULL, '200', '2017040330', '2021-05-25 21:30:04', NULL);
INSERT INTO `t_sys_log` VALUES (93, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-25 21:30:13', 'GET', NULL, '200', '2017040330', '2021-05-25 21:30:12', NULL);
INSERT INTO `t_sys_log` VALUES (94, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-25 21:30:17', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-25 21:30:16', NULL);
INSERT INTO `t_sys_log` VALUES (95, 'http://localhost:8888/manage/class', '获取所有课表', '2021-05-25 21:31:36', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-25 21:31:35', NULL);
INSERT INTO `t_sys_log` VALUES (96, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-05-25 21:31:55', 'GET', NULL, '200', '2017040330', '2021-05-25 21:31:55', NULL);
INSERT INTO `t_sys_log` VALUES (97, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-25 21:32:18', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-25 21:32:17', NULL);
INSERT INTO `t_sys_log` VALUES (98, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-25 21:32:19', 'GET', 'role=common', '200', '2017040329', '2021-05-25 21:32:19', NULL);
INSERT INTO `t_sys_log` VALUES (99, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-05-25 21:32:27', 'GET', NULL, '200', '2017040329', '2021-05-25 21:32:26', NULL);
INSERT INTO `t_sys_log` VALUES (100, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-05-25 21:32:30', 'GET', NULL, '200', '2017040329', '2021-05-25 21:32:30', NULL);
INSERT INTO `t_sys_log` VALUES (101, 'http://localhost:8888/manage/report/id', '根据报告id查询报告信息', '2021-05-25 21:32:34', 'GET', 'id=1', '200', '2017040329', '2021-05-25 21:32:34', NULL);
INSERT INTO `t_sys_log` VALUES (102, 'http://localhost:8888/manage/teams/userId', '通过用户账号获取团队', '2021-05-25 21:32:41', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-25 21:32:41', NULL);
INSERT INTO `t_sys_log` VALUES (103, 'http://localhost:8888/manage/teams', '获取所有团队', '2021-05-25 21:32:43', 'GET', NULL, '200', '2017040329', '2021-05-25 21:32:43', NULL);
INSERT INTO `t_sys_log` VALUES (104, 'http://localhost:8888/manage/teams/userId', '通过用户账号获取团队', '2021-05-25 21:32:46', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-25 21:32:46', NULL);
INSERT INTO `t_sys_log` VALUES (105, 'http://localhost:8888/manage/team/byTeamId', '根据团队id查询团队', '2021-05-25 21:32:46', 'GET', 'id=1', '200', '2017040329', '2021-05-25 21:32:46', NULL);
INSERT INTO `t_sys_log` VALUES (106, 'http://localhost:8888/manage/team/member', '获取团队成员', '2021-05-25 21:32:49', 'GET', 'id=1', '200', '2017040329', '2021-05-25 21:32:49', NULL);
INSERT INTO `t_sys_log` VALUES (107, 'http://localhost:8888/manage/label/all/byUserId', '通过用户id查询标签', '2021-05-25 21:32:49', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-25 21:32:49', NULL);
INSERT INTO `t_sys_log` VALUES (108, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-25 21:32:58', 'GET', NULL, '200', '2017040329', '2021-05-25 21:32:58', NULL);
INSERT INTO `t_sys_log` VALUES (109, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-25 21:32:58', 'GET', NULL, '200', '2017040329', '2021-05-25 21:32:58', NULL);
INSERT INTO `t_sys_log` VALUES (110, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-25 21:32:58', 'GET', NULL, '200', '2017040329', '2021-05-25 21:32:58', NULL);
INSERT INTO `t_sys_log` VALUES (111, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-25 21:33:00', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-25 21:32:59', NULL);
INSERT INTO `t_sys_log` VALUES (112, 'http://localhost:8888/manage/label/all/byUserId', '通过用户id查询标签', '2021-05-25 21:33:02', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-25 21:33:02', NULL);
INSERT INTO `t_sys_log` VALUES (113, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-25 21:33:03', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-25 21:33:03', NULL);
INSERT INTO `t_sys_log` VALUES (114, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-25 21:33:05', 'GET', NULL, '200', '2017040329', '2021-05-25 21:33:05', NULL);
INSERT INTO `t_sys_log` VALUES (115, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-25 21:33:05', 'GET', NULL, '200', '2017040329', '2021-05-25 21:33:05', NULL);
INSERT INTO `t_sys_log` VALUES (116, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-25 21:33:05', 'GET', NULL, '200', '2017040329', '2021-05-25 21:33:05', NULL);
INSERT INTO `t_sys_log` VALUES (117, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-25 21:33:10', 'GET', NULL, '200', '2017040329', '2021-05-25 21:33:09', NULL);
INSERT INTO `t_sys_log` VALUES (118, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-25 21:33:10', 'GET', NULL, '200', '2017040329', '2021-05-25 21:33:10', NULL);
INSERT INTO `t_sys_log` VALUES (119, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-25 21:33:10', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-25 21:33:10', NULL);
INSERT INTO `t_sys_log` VALUES (120, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-25 21:34:14', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-25 21:34:14', NULL);
INSERT INTO `t_sys_log` VALUES (121, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-25 21:34:14', 'GET', 'role=admin', '200', '2017040330', '2021-05-25 21:34:14', NULL);
INSERT INTO `t_sys_log` VALUES (122, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-25 21:34:17', 'GET', NULL, '200', '2017040330', '2021-05-25 21:34:16', NULL);
INSERT INTO `t_sys_log` VALUES (123, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-25 21:44:59', 'GET', NULL, '200', '2017040330', '2021-05-25 21:44:58', NULL);
INSERT INTO `t_sys_log` VALUES (124, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-25 21:45:00', 'GET', 'role=admin', '200', '2017040330', '2021-05-25 21:45:00', NULL);
INSERT INTO `t_sys_log` VALUES (125, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-25 21:56:16', 'GET', 'role=admin', '200', '2017040330', '2021-05-25 21:56:15', NULL);
INSERT INTO `t_sys_log` VALUES (126, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-25 21:56:15', 'GET', NULL, '200', '2017040330', '2021-05-25 21:56:15', NULL);
INSERT INTO `t_sys_log` VALUES (127, 'http://localhost:8888/manage/teams', '获取所有团队', '2021-05-25 21:58:44', 'GET', NULL, '200', '2017040330', '2021-05-25 21:58:43', NULL);
INSERT INTO `t_sys_log` VALUES (128, 'http://localhost:8888/manage/team/byTeamId', '根据团队id查询团队', '2021-05-25 21:58:48', 'GET', 'id=2', '200', '2017040330', '2021-05-25 21:58:48', NULL);
INSERT INTO `t_sys_log` VALUES (129, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-25 21:58:49', 'GET', NULL, '200', '2017040330', '2021-05-25 21:58:48', NULL);
INSERT INTO `t_sys_log` VALUES (130, 'http://localhost:8888/manage/team/member', '获取团队成员', '2021-05-25 21:58:50', 'GET', 'id=2', '200', '2017040330', '2021-05-25 21:58:49', NULL);
INSERT INTO `t_sys_log` VALUES (131, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 13:38:55', 'GET', 'role=admin', '200', '2017040330', '2021-05-31 13:38:55', NULL);
INSERT INTO `t_sys_log` VALUES (132, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 13:38:55', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 13:38:55', NULL);
INSERT INTO `t_sys_log` VALUES (133, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 13:39:02', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:02', NULL);
INSERT INTO `t_sys_log` VALUES (134, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-31 13:39:25', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:25', NULL);
INSERT INTO `t_sys_log` VALUES (135, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 13:39:28', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:27', NULL);
INSERT INTO `t_sys_log` VALUES (136, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-31 13:39:31', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:31', NULL);
INSERT INTO `t_sys_log` VALUES (137, 'http://localhost:8888/manage/role/user', '新增角色', '2021-05-31 13:39:35', 'POST', NULL, '200', '2017040330', '2021-05-31 13:39:34', NULL);
INSERT INTO `t_sys_log` VALUES (138, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-31 13:39:35', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:35', NULL);
INSERT INTO `t_sys_log` VALUES (139, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 13:39:43', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:42', NULL);
INSERT INTO `t_sys_log` VALUES (140, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:39:52', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:52', NULL);
INSERT INTO `t_sys_log` VALUES (141, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:39:53', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:52', NULL);
INSERT INTO `t_sys_log` VALUES (142, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:39:53', 'GET', NULL, '200', '2017040330', '2021-05-31 13:39:52', NULL);
INSERT INTO `t_sys_log` VALUES (143, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 13:43:19', 'POST', NULL, '200', '2017040330', '2021-05-31 13:43:19', NULL);
INSERT INTO `t_sys_log` VALUES (144, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:43:20', 'GET', NULL, '200', '2017040330', '2021-05-31 13:43:19', NULL);
INSERT INTO `t_sys_log` VALUES (145, 'http://localhost:8888/manage/document/upload', '上传或更新文件', '2021-05-31 13:43:53', 'POST', 'id=7', '200', '2017040330', '2021-05-31 13:43:52', NULL);
INSERT INTO `t_sys_log` VALUES (146, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:43:53', 'GET', NULL, '200', '2017040330', '2021-05-31 13:43:53', NULL);
INSERT INTO `t_sys_log` VALUES (147, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:44:07', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:06', NULL);
INSERT INTO `t_sys_log` VALUES (148, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:44:07', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:07', NULL);
INSERT INTO `t_sys_log` VALUES (149, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:44:07', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:07', NULL);
INSERT INTO `t_sys_log` VALUES (150, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:44:07', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:07', NULL);
INSERT INTO `t_sys_log` VALUES (151, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:44:12', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:11', NULL);
INSERT INTO `t_sys_log` VALUES (152, 'http://localhost:8888/manage/label', '添加标签', '2021-05-31 13:44:19', 'POST', NULL, '200', '2017040330', '2021-05-31 13:44:18', NULL);
INSERT INTO `t_sys_log` VALUES (153, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:44:19', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:19', NULL);
INSERT INTO `t_sys_log` VALUES (154, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:44:21', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:21', NULL);
INSERT INTO `t_sys_log` VALUES (155, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:44:21', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:21', NULL);
INSERT INTO `t_sys_log` VALUES (156, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:44:21', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:21', NULL);
INSERT INTO `t_sys_log` VALUES (157, 'http://localhost:8888/manage/classify/member', '为文献添加类别', '2021-05-31 13:44:28', 'POST', NULL, '200', '2017040330', '2021-05-31 13:44:28', NULL);
INSERT INTO `t_sys_log` VALUES (158, 'http://localhost:8888/manage/label/member', '为文献添加标签', '2021-05-31 13:44:31', 'POST', NULL, '200', '2017040330', '2021-05-31 13:44:31', NULL);
INSERT INTO `t_sys_log` VALUES (159, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:44:34', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:33', NULL);
INSERT INTO `t_sys_log` VALUES (160, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:44:34', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:33', NULL);
INSERT INTO `t_sys_log` VALUES (161, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:44:34', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:33', NULL);
INSERT INTO `t_sys_log` VALUES (162, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 13:44:40', 'GET', 'id=7&type=1', '200', '2017040330', '2021-05-31 13:44:39', NULL);
INSERT INTO `t_sys_log` VALUES (163, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 13:44:40', 'GET', 'id=7&userId=2017040330', '200', '2017040330', '2021-05-31 13:44:39', NULL);
INSERT INTO `t_sys_log` VALUES (164, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:44:51', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:50', NULL);
INSERT INTO `t_sys_log` VALUES (165, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:44:51', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:50', NULL);
INSERT INTO `t_sys_log` VALUES (166, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:44:51', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:50', NULL);
INSERT INTO `t_sys_log` VALUES (167, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:44:55', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:54', NULL);
INSERT INTO `t_sys_log` VALUES (168, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:44:56', 'GET', NULL, '200', '2017040330', '2021-05-31 13:44:56', NULL);
INSERT INTO `t_sys_log` VALUES (169, 'http://localhost:8888/manage/meetings', '获取所有会议', '2021-05-31 13:45:05', 'GET', NULL, '200', '2017040330', '2021-05-31 13:45:04', NULL);
INSERT INTO `t_sys_log` VALUES (170, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:45:12', 'GET', NULL, '200', '2017040330', '2021-05-31 13:45:11', NULL);
INSERT INTO `t_sys_log` VALUES (171, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:45:12', 'GET', NULL, '200', '2017040330', '2021-05-31 13:45:11', NULL);
INSERT INTO `t_sys_log` VALUES (172, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:45:12', 'GET', NULL, '200', '2017040330', '2021-05-31 13:45:11', NULL);
INSERT INTO `t_sys_log` VALUES (173, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 13:45:13', 'GET', 'id=7&type=1', '200', '2017040330', '2021-05-31 13:45:13', NULL);
INSERT INTO `t_sys_log` VALUES (174, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 13:45:15', 'GET', 'id=7&userId=2017040330', '200', '2017040330', '2021-05-31 13:45:14', NULL);
INSERT INTO `t_sys_log` VALUES (175, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:45:24', 'GET', NULL, '200', '2017040330', '2021-05-31 13:45:24', NULL);
INSERT INTO `t_sys_log` VALUES (176, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:45:24', 'GET', NULL, '200', '2017040330', '2021-05-31 13:45:24', NULL);
INSERT INTO `t_sys_log` VALUES (177, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:45:24', 'GET', NULL, '200', '2017040330', '2021-05-31 13:45:24', NULL);
INSERT INTO `t_sys_log` VALUES (178, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:46:02', 'GET', NULL, '200', '2017040330', '2021-05-31 13:46:01', NULL);
INSERT INTO `t_sys_log` VALUES (179, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:46:02', 'GET', NULL, '200', '2017040330', '2021-05-31 13:46:01', NULL);
INSERT INTO `t_sys_log` VALUES (180, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:46:02', 'GET', NULL, '200', '2017040330', '2021-05-31 13:46:01', NULL);
INSERT INTO `t_sys_log` VALUES (181, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 13:46:42', 'POST', NULL, '200', '2017040330', '2021-05-31 13:46:42', NULL);
INSERT INTO `t_sys_log` VALUES (182, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:46:43', 'GET', NULL, '200', '2017040330', '2021-05-31 13:46:42', NULL);
INSERT INTO `t_sys_log` VALUES (183, 'http://localhost:8888/manage/document', '删除文献', '2021-05-31 13:47:12', 'DELETE', NULL, '200', '2017040330', '2021-05-31 13:47:11', NULL);
INSERT INTO `t_sys_log` VALUES (184, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:47:12', 'GET', NULL, '200', '2017040330', '2021-05-31 13:47:12', NULL);
INSERT INTO `t_sys_log` VALUES (185, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 13:48:04', 'POST', NULL, '200', '2017040330', '2021-05-31 13:48:03', NULL);
INSERT INTO `t_sys_log` VALUES (186, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:48:04', 'GET', NULL, '200', '2017040330', '2021-05-31 13:48:04', NULL);
INSERT INTO `t_sys_log` VALUES (187, 'http://localhost:8888/manage/document/upload', '上传或更新文件', '2021-05-31 13:48:19', 'POST', 'id=9', '200', '2017040330', '2021-05-31 13:48:18', NULL);
INSERT INTO `t_sys_log` VALUES (188, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:48:19', 'GET', NULL, '200', '2017040330', '2021-05-31 13:48:19', NULL);
INSERT INTO `t_sys_log` VALUES (189, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 13:49:07', 'POST', NULL, '200', '2017040330', '2021-05-31 13:49:07', NULL);
INSERT INTO `t_sys_log` VALUES (190, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:49:08', 'GET', NULL, '200', '2017040330', '2021-05-31 13:49:07', NULL);
INSERT INTO `t_sys_log` VALUES (191, 'http://localhost:8888/manage/document/upload', '上传或更新文件', '2021-05-31 13:49:22', 'POST', 'id=10', '200', '2017040330', '2021-05-31 13:49:21', NULL);
INSERT INTO `t_sys_log` VALUES (192, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:49:22', 'GET', NULL, '200', '2017040330', '2021-05-31 13:49:22', NULL);
INSERT INTO `t_sys_log` VALUES (193, 'http://localhost:8888/manage/document/searchByTitle', '根据文献标题查询', '2021-05-31 13:49:36', 'GET', 'title=%E5%88%86%E7%A6%BB%E6%8A%80', '200', '2017040330', '2021-05-31 13:49:35', NULL);
INSERT INTO `t_sys_log` VALUES (194, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:49:43', 'GET', NULL, '200', '2017040330', '2021-05-31 13:49:43', NULL);
INSERT INTO `t_sys_log` VALUES (195, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:49:43', 'GET', NULL, '200', '2017040330', '2021-05-31 13:49:43', NULL);
INSERT INTO `t_sys_log` VALUES (196, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:49:43', 'GET', NULL, '200', '2017040330', '2021-05-31 13:49:43', NULL);
INSERT INTO `t_sys_log` VALUES (197, 'http://localhost:8888/manage/document/classify', '根据类别查询文献', '2021-05-31 13:49:50', 'GET', 'id=2', '200', '2017040330', '2021-05-31 13:49:50', NULL);
INSERT INTO `t_sys_log` VALUES (198, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:49:53', 'GET', NULL, '200', '2017040330', '2021-05-31 13:49:52', NULL);
INSERT INTO `t_sys_log` VALUES (199, 'http://localhost:8888/manage/document/label', '根据标签查询文献', '2021-05-31 13:49:55', 'GET', 'id=2', '200', '2017040330', '2021-05-31 13:49:54', NULL);
INSERT INTO `t_sys_log` VALUES (200, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:49:57', 'GET', NULL, '200', '2017040330', '2021-05-31 13:49:57', NULL);
INSERT INTO `t_sys_log` VALUES (201, 'http://localhost:8888/manage/document/searchByTitle', '根据文献标题查询', '2021-05-31 13:50:06', 'GET', 'title=%E8%AE%BE%E8%AE%A1', '200', '2017040330', '2021-05-31 13:50:06', NULL);
INSERT INTO `t_sys_log` VALUES (202, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:50:09', 'GET', NULL, '200', '2017040330', '2021-05-31 13:50:08', NULL);
INSERT INTO `t_sys_log` VALUES (203, 'http://localhost:8888/manage/document/searchByAuthor', '根据文献作者查询', '2021-05-31 13:50:16', 'GET', 'author=%E5%BC%A0%E5%8D%AB%E5%8D%8E', '200', '2017040330', '2021-05-31 13:50:15', NULL);
INSERT INTO `t_sys_log` VALUES (204, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:50:20', 'GET', NULL, '200', '2017040330', '2021-05-31 13:50:20', NULL);
INSERT INTO `t_sys_log` VALUES (205, 'http://localhost:8888/manage/document/searchByPlace', '根据文献出版单位查询', '2021-05-31 13:50:25', 'GET', 'place=%E5%8D%97%E4%BA%AC', '200', '2017040330', '2021-05-31 13:50:25', NULL);
INSERT INTO `t_sys_log` VALUES (206, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:50:29', 'GET', NULL, '200', '2017040330', '2021-05-31 13:50:28', NULL);
INSERT INTO `t_sys_log` VALUES (207, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 13:50:37', 'GET', NULL, '200', '2017040330', '2021-05-31 13:50:36', NULL);
INSERT INTO `t_sys_log` VALUES (208, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 13:50:37', 'GET', NULL, '200', '2017040330', '2021-05-31 13:50:36', NULL);
INSERT INTO `t_sys_log` VALUES (209, 'http://localhost:8888/manage/task/id', '查看任务详情', '2021-05-31 13:51:03', 'GET', 'id=1', '200', '2017040330', '2021-05-31 13:51:02', NULL);
INSERT INTO `t_sys_log` VALUES (210, 'http://localhost:8888/manage/task/member', '获取任务成员', '2021-05-31 13:51:03', 'GET', 'id=1', '200', '2017040330', '2021-05-31 13:51:03', NULL);
INSERT INTO `t_sys_log` VALUES (211, 'http://localhost:8888/manage/meetings', '获取所有会议', '2021-05-31 13:51:10', 'GET', NULL, '200', '2017040330', '2021-05-31 13:51:10', NULL);
INSERT INTO `t_sys_log` VALUES (212, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 13:51:26', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 13:51:25', NULL);
INSERT INTO `t_sys_log` VALUES (213, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 13:51:31', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 13:51:30', NULL);
INSERT INTO `t_sys_log` VALUES (214, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 13:51:31', 'GET', 'role=admin', '200', '2017040330', '2021-05-31 13:51:30', NULL);
INSERT INTO `t_sys_log` VALUES (215, 'http://localhost:8888/manage/class', '获取所有课表', '2021-05-31 13:51:41', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 13:51:41', NULL);
INSERT INTO `t_sys_log` VALUES (216, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-05-31 13:51:48', 'GET', NULL, '200', '2017040330', '2021-05-31 13:51:47', NULL);
INSERT INTO `t_sys_log` VALUES (217, 'http://localhost:8888/manage/softwares', '获取所有软件信息', '2021-05-31 13:51:55', 'GET', NULL, '200', '2017040330', '2021-05-31 13:51:54', NULL);
INSERT INTO `t_sys_log` VALUES (218, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 13:51:56', 'GET', NULL, '200', '2017040330', '2021-05-31 13:51:56', NULL);
INSERT INTO `t_sys_log` VALUES (219, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 13:51:56', 'GET', NULL, '200', '2017040330', '2021-05-31 13:51:56', NULL);
INSERT INTO `t_sys_log` VALUES (220, 'http://localhost:8888/manage/meetings', '获取所有会议', '2021-05-31 13:52:00', 'GET', NULL, '200', '2017040330', '2021-05-31 13:52:00', NULL);
INSERT INTO `t_sys_log` VALUES (221, 'http://localhost:8888/manage/meeting', '更新会议', '2021-05-31 13:52:17', 'PUT', NULL, '200', '2017040330', '2021-05-31 13:52:17', NULL);
INSERT INTO `t_sys_log` VALUES (222, 'http://localhost:8888/manage/meetings', '获取所有会议', '2021-05-31 13:52:17', 'GET', NULL, '200', '2017040330', '2021-05-31 13:52:17', NULL);
INSERT INTO `t_sys_log` VALUES (223, 'http://localhost:8888/manage/softwares', '获取所有软件信息', '2021-05-31 13:52:29', 'GET', NULL, '200', '2017040330', '2021-05-31 13:52:29', NULL);
INSERT INTO `t_sys_log` VALUES (224, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 13:52:44', 'GET', NULL, '200', '2017040330', '2021-05-31 13:52:43', NULL);
INSERT INTO `t_sys_log` VALUES (225, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 13:52:44', 'GET', NULL, '200', '2017040330', '2021-05-31 13:52:44', NULL);
INSERT INTO `t_sys_log` VALUES (226, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:52:48', 'GET', NULL, '200', '2017040330', '2021-05-31 13:52:48', NULL);
INSERT INTO `t_sys_log` VALUES (227, 'http://localhost:8888/manage/teams', '获取所有团队', '2021-05-31 13:52:54', 'GET', NULL, '200', '2017040330', '2021-05-31 13:52:54', NULL);
INSERT INTO `t_sys_log` VALUES (228, 'http://localhost:8888/manage/teams', '获取所有团队', '2021-05-31 13:52:58', 'GET', NULL, '200', '2017040330', '2021-05-31 13:52:57', NULL);
INSERT INTO `t_sys_log` VALUES (229, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-31 13:53:05', 'GET', NULL, '200', '2017040330', '2021-05-31 13:53:05', NULL);
INSERT INTO `t_sys_log` VALUES (230, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-31 13:55:22', 'GET', NULL, '200', '2017040330', '2021-05-31 13:55:21', NULL);
INSERT INTO `t_sys_log` VALUES (231, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-31 13:58:29', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:28', NULL);
INSERT INTO `t_sys_log` VALUES (232, 'http://localhost:8888/manage/teams', '获取所有团队', '2021-05-31 13:58:31', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:31', NULL);
INSERT INTO `t_sys_log` VALUES (233, 'http://localhost:8888/manage/team/byTeamId', '根据团队id查询团队', '2021-05-31 13:58:37', 'GET', 'id=2', '200', '2017040330', '2021-05-31 13:58:36', NULL);
INSERT INTO `t_sys_log` VALUES (234, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 13:58:37', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:36', NULL);
INSERT INTO `t_sys_log` VALUES (235, 'http://localhost:8888/manage/team/member', '获取团队成员', '2021-05-31 13:58:37', 'GET', 'id=2', '200', '2017040330', '2021-05-31 13:58:37', NULL);
INSERT INTO `t_sys_log` VALUES (236, 'http://localhost:8888/manage/team/member', '添加团队成员', '2021-05-31 13:58:46', 'POST', NULL, '200', '2017040330', '2021-05-31 13:58:45', NULL);
INSERT INTO `t_sys_log` VALUES (237, 'http://localhost:8888/manage/team/member', '获取团队成员', '2021-05-31 13:58:46', 'GET', 'id=2', '200', '2017040330', '2021-05-31 13:58:45', NULL);
INSERT INTO `t_sys_log` VALUES (238, 'http://localhost:8888/manage/teams', '获取所有团队', '2021-05-31 13:58:48', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:47', NULL);
INSERT INTO `t_sys_log` VALUES (239, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:58:50', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:50', NULL);
INSERT INTO `t_sys_log` VALUES (240, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:58:51', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:50', NULL);
INSERT INTO `t_sys_log` VALUES (241, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:58:51', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:50', NULL);
INSERT INTO `t_sys_log` VALUES (242, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 13:58:53', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:52', NULL);
INSERT INTO `t_sys_log` VALUES (243, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:58:53', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:52', NULL);
INSERT INTO `t_sys_log` VALUES (244, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:58:53', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:52', NULL);
INSERT INTO `t_sys_log` VALUES (245, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 13:58:54', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:54', NULL);
INSERT INTO `t_sys_log` VALUES (246, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 13:58:56', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:56', NULL);
INSERT INTO `t_sys_log` VALUES (247, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 13:58:58', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:58', NULL);
INSERT INTO `t_sys_log` VALUES (248, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 13:58:59', 'GET', NULL, '200', '2017040330', '2021-05-31 13:58:58', NULL);
INSERT INTO `t_sys_log` VALUES (249, 'http://localhost:8888/manage/meetings', '获取所有会议', '2021-05-31 13:59:01', 'GET', NULL, '200', '2017040330', '2021-05-31 13:59:01', NULL);
INSERT INTO `t_sys_log` VALUES (250, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-05-31 13:59:02', 'GET', NULL, '200', '2017040330', '2021-05-31 13:59:02', NULL);
INSERT INTO `t_sys_log` VALUES (251, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-05-31 13:59:11', 'GET', NULL, '200', '2017040330', '2021-05-31 13:59:11', NULL);
INSERT INTO `t_sys_log` VALUES (252, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 13:59:12', 'GET', 'role=admin', '200', '2017040330', '2021-05-31 13:59:11', NULL);
INSERT INTO `t_sys_log` VALUES (253, 'http://localhost:8888/manage/class', '获取所有课表', '2021-05-31 14:00:43', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 14:00:43', NULL);
INSERT INTO `t_sys_log` VALUES (254, 'http://localhost:8888/manage/class', '获取所有课表', '2021-05-31 14:00:48', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 14:00:48', NULL);
INSERT INTO `t_sys_log` VALUES (255, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:00:48', 'GET', 'role=admin', '200', '2017040330', '2021-05-31 14:00:48', NULL);
INSERT INTO `t_sys_log` VALUES (256, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-05-31 14:00:53', 'GET', NULL, '200', '2017040330', '2021-05-31 14:00:53', NULL);
INSERT INTO `t_sys_log` VALUES (257, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-31 14:01:00', 'GET', NULL, '200', '2017040330', '2021-05-31 14:00:59', NULL);
INSERT INTO `t_sys_log` VALUES (258, 'http://localhost:8888/manage/class', '获取所有课表', '2021-05-31 14:01:05', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 14:01:05', NULL);
INSERT INTO `t_sys_log` VALUES (259, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:01:32', 'GET', NULL, '200', '2017040330', '2021-05-31 14:01:32', NULL);
INSERT INTO `t_sys_log` VALUES (260, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-31 14:02:42', 'GET', NULL, '200', '2017040330', '2021-05-31 14:02:42', NULL);
INSERT INTO `t_sys_log` VALUES (261, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 14:02:49', 'GET', NULL, '200', '2017040330', '2021-05-31 14:02:48', NULL);
INSERT INTO `t_sys_log` VALUES (262, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-31 14:02:51', 'GET', NULL, '200', '2017040330', '2021-05-31 14:02:50', NULL);
INSERT INTO `t_sys_log` VALUES (263, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:02:52', 'GET', NULL, '200', '2017040330', '2021-05-31 14:02:52', NULL);
INSERT INTO `t_sys_log` VALUES (264, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:02:52', 'GET', NULL, '200', '2017040330', '2021-05-31 14:02:52', NULL);
INSERT INTO `t_sys_log` VALUES (265, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:02:52', 'GET', NULL, '200', '2017040330', '2021-05-31 14:02:52', NULL);
INSERT INTO `t_sys_log` VALUES (266, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-31 14:02:54', 'GET', NULL, '200', '2017040330', '2021-05-31 14:02:54', NULL);
INSERT INTO `t_sys_log` VALUES (267, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-05-31 14:03:05', 'GET', NULL, '200', '2017040330', '2021-05-31 14:03:04', NULL);
INSERT INTO `t_sys_log` VALUES (268, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:03:05', 'GET', 'role=admin', '200', '2017040330', '2021-05-31 14:03:04', NULL);
INSERT INTO `t_sys_log` VALUES (269, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-05-31 14:05:09', 'GET', NULL, '200', '2017040330', '2021-05-31 14:05:09', NULL);
INSERT INTO `t_sys_log` VALUES (270, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:05:36', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:05:35', NULL);
INSERT INTO `t_sys_log` VALUES (271, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:05:36', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:05:35', NULL);
INSERT INTO `t_sys_log` VALUES (272, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:05:43', 'GET', NULL, '200', '2017040329', '2021-05-31 14:05:42', NULL);
INSERT INTO `t_sys_log` VALUES (273, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:05:43', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:05:42', NULL);
INSERT INTO `t_sys_log` VALUES (274, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:05:43', 'GET', NULL, '200', '2017040329', '2021-05-31 14:05:42', NULL);
INSERT INTO `t_sys_log` VALUES (275, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:05:45', 'GET', NULL, '200', '2017040329', '2021-05-31 14:05:44', NULL);
INSERT INTO `t_sys_log` VALUES (276, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:05:45', 'GET', NULL, '200', '2017040329', '2021-05-31 14:05:45', NULL);
INSERT INTO `t_sys_log` VALUES (277, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:05:45', 'GET', NULL, '200', '2017040329', '2021-05-31 14:05:45', NULL);
INSERT INTO `t_sys_log` VALUES (278, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-31 14:05:48', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:05:48', NULL);
INSERT INTO `t_sys_log` VALUES (279, 'http://localhost:8888/manage/label/all/byUserId', '通过用户id查询标签', '2021-05-31 14:05:50', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:05:50', NULL);
INSERT INTO `t_sys_log` VALUES (280, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:05:53', 'GET', NULL, '200', '2017040329', '2021-05-31 14:05:53', NULL);
INSERT INTO `t_sys_log` VALUES (281, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:05:53', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:05:53', NULL);
INSERT INTO `t_sys_log` VALUES (282, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:05:53', 'GET', NULL, '200', '2017040329', '2021-05-31 14:05:53', NULL);
INSERT INTO `t_sys_log` VALUES (283, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 14:06:34', 'POST', NULL, '200', '2017040329', '2021-05-31 14:06:33', NULL);
INSERT INTO `t_sys_log` VALUES (284, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:06:34', 'GET', NULL, '200', '2017040329', '2021-05-31 14:06:34', NULL);
INSERT INTO `t_sys_log` VALUES (285, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:08:42', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:08:41', NULL);
INSERT INTO `t_sys_log` VALUES (286, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:08:42', 'GET', NULL, '200', '2017040329', '2021-05-31 14:08:41', NULL);
INSERT INTO `t_sys_log` VALUES (287, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:08:42', 'GET', NULL, '200', '2017040329', '2021-05-31 14:08:42', NULL);
INSERT INTO `t_sys_log` VALUES (288, 'http://localhost:8888/manage/document/upload', '上传或更新文件', '2021-05-31 14:09:16', 'POST', 'id=11', '200', '2017040329', '2021-05-31 14:09:15', NULL);
INSERT INTO `t_sys_log` VALUES (289, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:09:16', 'GET', NULL, '200', '2017040329', '2021-05-31 14:09:16', NULL);
INSERT INTO `t_sys_log` VALUES (290, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:09:54', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:09:53', NULL);
INSERT INTO `t_sys_log` VALUES (291, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:09:54', 'GET', NULL, '200', '2017040329', '2021-05-31 14:09:54', NULL);
INSERT INTO `t_sys_log` VALUES (292, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:09:54', 'GET', NULL, '200', '2017040329', '2021-05-31 14:09:54', NULL);
INSERT INTO `t_sys_log` VALUES (293, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:10:08', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:10:08', NULL);
INSERT INTO `t_sys_log` VALUES (294, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:10:08', 'GET', NULL, '200', '2017040329', '2021-05-31 14:10:08', NULL);
INSERT INTO `t_sys_log` VALUES (295, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:10:08', 'GET', NULL, '200', '2017040329', '2021-05-31 14:10:08', NULL);
INSERT INTO `t_sys_log` VALUES (296, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:10:13', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:10:12', NULL);
INSERT INTO `t_sys_log` VALUES (297, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:10:13', 'GET', NULL, '200', '2017040329', '2021-05-31 14:10:12', NULL);
INSERT INTO `t_sys_log` VALUES (298, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:10:13', 'GET', NULL, '200', '2017040329', '2021-05-31 14:10:12', NULL);
INSERT INTO `t_sys_log` VALUES (299, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 14:11:04', 'POST', NULL, '200', '2017040329', '2021-05-31 14:11:04', NULL);
INSERT INTO `t_sys_log` VALUES (300, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 14:12:25', 'POST', NULL, '200', '2017040329', '2021-05-31 14:12:24', NULL);
INSERT INTO `t_sys_log` VALUES (301, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 14:12:30', 'POST', NULL, '200', '2017040329', '2021-05-31 14:12:30', NULL);
INSERT INTO `t_sys_log` VALUES (302, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:12:31', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:12:30', NULL);
INSERT INTO `t_sys_log` VALUES (303, 'http://localhost:8888/manage/document/upload', '上传或更新文件', '2021-05-31 14:12:53', 'POST', 'id=12', '200', '2017040329', '2021-05-31 14:12:52', NULL);
INSERT INTO `t_sys_log` VALUES (304, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:12:53', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:12:53', NULL);
INSERT INTO `t_sys_log` VALUES (305, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:14:24', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:14:23', NULL);
INSERT INTO `t_sys_log` VALUES (306, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:14:24', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:14:23', NULL);
INSERT INTO `t_sys_log` VALUES (307, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:14:24', 'GET', NULL, '200', '2017040329', '2021-05-31 14:14:23', NULL);
INSERT INTO `t_sys_log` VALUES (308, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:14:24', 'GET', NULL, '200', '2017040329', '2021-05-31 14:14:23', NULL);
INSERT INTO `t_sys_log` VALUES (309, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:16:21', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:16:21', NULL);
INSERT INTO `t_sys_log` VALUES (310, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:16:32', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:16:31', NULL);
INSERT INTO `t_sys_log` VALUES (311, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:16:32', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:16:31', NULL);
INSERT INTO `t_sys_log` VALUES (312, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 14:16:40', 'GET', NULL, '200', '2017040329', '2021-05-31 14:16:40', NULL);
INSERT INTO `t_sys_log` VALUES (313, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 14:16:40', 'GET', NULL, '200', '2017040329', '2021-05-31 14:16:40', NULL);
INSERT INTO `t_sys_log` VALUES (314, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:17:05', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:17:05', NULL);
INSERT INTO `t_sys_log` VALUES (315, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 14:17:05', 'GET', NULL, '200', '2017040329', '2021-05-31 14:17:05', NULL);
INSERT INTO `t_sys_log` VALUES (316, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 14:17:05', 'GET', NULL, '200', '2017040329', '2021-05-31 14:17:05', NULL);
INSERT INTO `t_sys_log` VALUES (317, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 14:22:16', 'GET', NULL, '200', '2017040329', '2021-05-31 14:22:15', NULL);
INSERT INTO `t_sys_log` VALUES (318, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 14:22:16', 'GET', NULL, '200', '2017040329', '2021-05-31 14:22:15', NULL);
INSERT INTO `t_sys_log` VALUES (319, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:22:16', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:22:15', NULL);
INSERT INTO `t_sys_log` VALUES (320, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:22:51', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:22:51', NULL);
INSERT INTO `t_sys_log` VALUES (321, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:23:45', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:23:45', NULL);
INSERT INTO `t_sys_log` VALUES (322, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:23:45', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:23:45', NULL);
INSERT INTO `t_sys_log` VALUES (323, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:24:06', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:24:06', NULL);
INSERT INTO `t_sys_log` VALUES (324, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:24:06', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:24:06', NULL);
INSERT INTO `t_sys_log` VALUES (325, 'http://localhost:8888/manage/teams/userId', '通过用户账号获取团队', '2021-05-31 14:24:13', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:24:13', NULL);
INSERT INTO `t_sys_log` VALUES (326, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:24:17', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:24:16', NULL);
INSERT INTO `t_sys_log` VALUES (327, 'http://localhost:8888/manage/teams/userId', '通过用户账号获取团队', '2021-05-31 14:24:17', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:24:16', NULL);
INSERT INTO `t_sys_log` VALUES (328, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-31 14:24:55', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:24:54', NULL);
INSERT INTO `t_sys_log` VALUES (329, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:26:25', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:26:24', NULL);
INSERT INTO `t_sys_log` VALUES (330, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-31 14:26:25', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:26:24', NULL);
INSERT INTO `t_sys_log` VALUES (331, 'http://localhost:8888/manage/classify', '添加类别信息', '2021-05-31 14:34:33', 'POST', NULL, '200', '2017040329', '2021-05-31 14:34:32', NULL);
INSERT INTO `t_sys_log` VALUES (332, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-31 14:34:33', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:34:33', NULL);
INSERT INTO `t_sys_log` VALUES (333, 'http://localhost:8888/manage/classify', '添加类别信息', '2021-05-31 14:34:44', 'POST', NULL, '200', '2017040329', '2021-05-31 14:34:44', NULL);
INSERT INTO `t_sys_log` VALUES (334, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-31 14:34:45', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:34:44', NULL);
INSERT INTO `t_sys_log` VALUES (335, 'http://localhost:8888/manage/classify', '添加类别信息', '2021-05-31 14:35:17', 'POST', NULL, '200', '2017040329', '2021-05-31 14:35:16', NULL);
INSERT INTO `t_sys_log` VALUES (336, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-31 14:35:17', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:35:16', NULL);
INSERT INTO `t_sys_log` VALUES (337, 'http://localhost:8888/manage/label/all/byUserId', '通过用户id查询标签', '2021-05-31 14:35:22', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:35:21', NULL);
INSERT INTO `t_sys_log` VALUES (338, 'http://localhost:8888/manage/label', '添加标签', '2021-05-31 14:35:32', 'POST', NULL, '200', '2017040329', '2021-05-31 14:35:32', NULL);
INSERT INTO `t_sys_log` VALUES (339, 'http://localhost:8888/manage/label/all/byUserId', '通过用户id查询标签', '2021-05-31 14:35:33', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:35:32', NULL);
INSERT INTO `t_sys_log` VALUES (340, 'http://localhost:8888/manage/label', '删除标签', '2021-05-31 14:35:42', 'DELETE', NULL, '200', '2017040329', '2021-05-31 14:35:41', NULL);
INSERT INTO `t_sys_log` VALUES (341, 'http://localhost:8888/manage/label/all/byUserId', '通过用户id查询标签', '2021-05-31 14:35:43', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:35:42', NULL);
INSERT INTO `t_sys_log` VALUES (342, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-05-31 14:35:47', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:35:46', NULL);
INSERT INTO `t_sys_log` VALUES (343, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 14:35:51', 'GET', NULL, '200', '2017040329', '2021-05-31 14:35:51', NULL);
INSERT INTO `t_sys_log` VALUES (344, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 14:35:51', 'GET', NULL, '200', '2017040329', '2021-05-31 14:35:51', NULL);
INSERT INTO `t_sys_log` VALUES (345, 'http://localhost:8888/manage/task/id', '查看任务详情', '2021-05-31 14:36:05', 'GET', 'id=1', '200', '2017040329', '2021-05-31 14:36:04', NULL);
INSERT INTO `t_sys_log` VALUES (346, 'http://localhost:8888/manage/task/member', '获取任务成员', '2021-05-31 14:36:06', 'GET', 'id=1', '200', '2017040329', '2021-05-31 14:36:06', NULL);
INSERT INTO `t_sys_log` VALUES (347, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 14:36:11', 'GET', NULL, '200', '2017040329', '2021-05-31 14:36:11', NULL);
INSERT INTO `t_sys_log` VALUES (348, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 14:36:12', 'GET', NULL, '200', '2017040329', '2021-05-31 14:36:11', NULL);
INSERT INTO `t_sys_log` VALUES (349, 'http://localhost:8888/manage/task/id', '查看任务详情', '2021-05-31 14:36:13', 'GET', 'id=2', '200', '2017040329', '2021-05-31 14:36:13', NULL);
INSERT INTO `t_sys_log` VALUES (350, 'http://localhost:8888/manage/task/member', '获取任务成员', '2021-05-31 14:36:13', 'GET', 'id=2', '200', '2017040329', '2021-05-31 14:36:13', NULL);
INSERT INTO `t_sys_log` VALUES (351, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-05-31 14:36:17', 'GET', NULL, '200', '2017040329', '2021-05-31 14:36:16', NULL);
INSERT INTO `t_sys_log` VALUES (352, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-05-31 14:36:17', 'GET', NULL, '200', '2017040329', '2021-05-31 14:36:17', NULL);
INSERT INTO `t_sys_log` VALUES (353, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-05-31 14:36:34', 'GET', NULL, '200', '2017040329', '2021-05-31 14:36:33', NULL);
INSERT INTO `t_sys_log` VALUES (354, 'http://localhost:8888/manage/report/id', '根据报告id查询报告信息', '2021-05-31 14:36:37', 'GET', 'id=2', '200', '2017040329', '2021-05-31 14:36:37', NULL);
INSERT INTO `t_sys_log` VALUES (355, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-05-31 14:36:42', 'GET', NULL, '200', '2017040329', '2021-05-31 14:36:41', NULL);
INSERT INTO `t_sys_log` VALUES (356, 'http://localhost:8888/manage/report/id', '根据报告id查询报告信息', '2021-05-31 14:36:43', 'GET', 'id=1', '200', '2017040329', '2021-05-31 14:36:42', NULL);
INSERT INTO `t_sys_log` VALUES (357, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-05-31 14:36:46', 'GET', NULL, '200', '2017040329', '2021-05-31 14:36:45', NULL);
INSERT INTO `t_sys_log` VALUES (358, 'http://localhost:8888/manage/softwares/userId', '通过用户账户获取软件信息', '2021-05-31 14:36:50', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:36:50', NULL);
INSERT INTO `t_sys_log` VALUES (359, 'http://localhost:8888/manage/softwares', '获取所有软件信息', '2021-05-31 14:37:11', 'GET', NULL, '200', '2017040329', '2021-05-31 14:37:11', NULL);
INSERT INTO `t_sys_log` VALUES (360, 'http://localhost:8888/manage/achievement/byUploadUser', '通过上传者id获取成果', '2021-05-31 14:37:19', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:37:19', NULL);
INSERT INTO `t_sys_log` VALUES (361, 'http://localhost:8888/manage/achievement/byUploadUser', '通过上传者id获取成果', '2021-05-31 14:45:36', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:45:35', NULL);
INSERT INTO `t_sys_log` VALUES (362, 'http://localhost:8888/manage/achievement', '添加个人成果', '2021-05-31 14:46:20', 'POST', NULL, '200', '2017040329', '2021-05-31 14:46:19', NULL);
INSERT INTO `t_sys_log` VALUES (363, 'http://localhost:8888/manage/achievement/byUploadUser', '通过上传者id获取成果', '2021-05-31 14:46:20', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:46:20', NULL);
INSERT INTO `t_sys_log` VALUES (364, 'http://localhost:8888/manage/achievements', '获取所有成果', '2021-05-31 14:46:24', 'GET', NULL, '200', '2017040329', '2021-05-31 14:46:23', NULL);
INSERT INTO `t_sys_log` VALUES (365, 'http://localhost:8888/manage/achievements', '获取所有成果', '2021-05-31 14:46:53', 'GET', NULL, '200', '2017040329', '2021-05-31 14:46:53', NULL);
INSERT INTO `t_sys_log` VALUES (366, 'http://localhost:8888/manage/achievements', '获取所有成果', '2021-05-31 14:47:33', 'GET', NULL, '200', '2017040329', '2021-05-31 14:47:32', NULL);
INSERT INTO `t_sys_log` VALUES (367, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:47:33', 'GET', 'role=common', '200', '2017040329', '2021-05-31 14:47:32', NULL);
INSERT INTO `t_sys_log` VALUES (368, 'http://localhost:8888/manage/achievement/byUploadUser', '通过上传者id获取成果', '2021-05-31 14:47:44', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:47:44', NULL);
INSERT INTO `t_sys_log` VALUES (369, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-05-31 14:48:04', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:03', NULL);
INSERT INTO `t_sys_log` VALUES (370, 'http://localhost:8888/manage/meetings', '获取所有会议', '2021-05-31 14:48:10', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:09', NULL);
INSERT INTO `t_sys_log` VALUES (371, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:48:12', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:48:12', NULL);
INSERT INTO `t_sys_log` VALUES (372, 'http://localhost:8888/manage/softwares', '获取所有软件信息', '2021-05-31 14:48:29', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:29', NULL);
INSERT INTO `t_sys_log` VALUES (373, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:48:34', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:33', NULL);
INSERT INTO `t_sys_log` VALUES (374, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:48:34', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:48:33', NULL);
INSERT INTO `t_sys_log` VALUES (375, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:48:34', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:34', NULL);
INSERT INTO `t_sys_log` VALUES (376, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:48:45', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:45', NULL);
INSERT INTO `t_sys_log` VALUES (377, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:48:45', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:45', NULL);
INSERT INTO `t_sys_log` VALUES (378, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:48:45', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:45', NULL);
INSERT INTO `t_sys_log` VALUES (379, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:48:51', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:50', NULL);
INSERT INTO `t_sys_log` VALUES (380, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:48:51', 'GET', NULL, '200', '2017040329', '2021-05-31 14:48:50', NULL);
INSERT INTO `t_sys_log` VALUES (381, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:48:51', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:48:50', NULL);
INSERT INTO `t_sys_log` VALUES (382, 'http://localhost:8888/manage/document', '添加文献', '2021-05-31 14:50:20', 'POST', NULL, '200', '2017040329', '2021-05-31 14:50:20', NULL);
INSERT INTO `t_sys_log` VALUES (383, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:50:20', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:50:20', NULL);
INSERT INTO `t_sys_log` VALUES (384, 'http://localhost:8888/manage/document/upload', '上传或更新文件', '2021-05-31 14:50:55', 'POST', 'id=13', '200', '2017040329', '2021-05-31 14:50:54', NULL);
INSERT INTO `t_sys_log` VALUES (385, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:50:55', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:50:55', NULL);
INSERT INTO `t_sys_log` VALUES (386, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:51:00', 'GET', NULL, '200', '2017040329', '2021-05-31 14:50:59', NULL);
INSERT INTO `t_sys_log` VALUES (387, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:51:00', 'GET', NULL, '200', '2017040329', '2021-05-31 14:50:59', NULL);
INSERT INTO `t_sys_log` VALUES (388, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:51:00', 'GET', NULL, '200', '2017040329', '2021-05-31 14:50:59', NULL);
INSERT INTO `t_sys_log` VALUES (389, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 14:51:03', 'GET', 'id=9&userId=2017040329', '200', '2017040329', '2021-05-31 14:51:02', NULL);
INSERT INTO `t_sys_log` VALUES (390, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:51:03', 'GET', 'id=9&type=1', '200', '2017040329', '2021-05-31 14:51:03', NULL);
INSERT INTO `t_sys_log` VALUES (391, 'http://localhost:8888/manage/like/add', '添加点赞信息', '2021-05-31 14:51:07', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:06', NULL);
INSERT INTO `t_sys_log` VALUES (392, 'http://localhost:8888/manage/score/add', '添加评分', '2021-05-31 14:51:08', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:08', NULL);
INSERT INTO `t_sys_log` VALUES (393, 'http://localhost:8888/manage/comment', '添加评论', '2021-05-31 14:51:18', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:17', NULL);
INSERT INTO `t_sys_log` VALUES (394, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:51:18', 'GET', 'id=9&type=1', '200', '2017040329', '2021-05-31 14:51:17', NULL);
INSERT INTO `t_sys_log` VALUES (395, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:51:24', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:23', NULL);
INSERT INTO `t_sys_log` VALUES (396, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:51:24', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:23', NULL);
INSERT INTO `t_sys_log` VALUES (397, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:51:24', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:23', NULL);
INSERT INTO `t_sys_log` VALUES (398, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:51:27', 'GET', 'id=13&type=1', '200', '2017040329', '2021-05-31 14:51:27', NULL);
INSERT INTO `t_sys_log` VALUES (399, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 14:51:27', 'GET', 'id=13&userId=2017040329', '200', '2017040329', '2021-05-31 14:51:27', NULL);
INSERT INTO `t_sys_log` VALUES (400, 'http://localhost:8888/manage/like/add', '添加点赞信息', '2021-05-31 14:51:29', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:29', NULL);
INSERT INTO `t_sys_log` VALUES (401, 'http://localhost:8888/manage/score/add', '添加评分', '2021-05-31 14:51:31', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:31', NULL);
INSERT INTO `t_sys_log` VALUES (402, 'http://localhost:8888/manage/comment', '添加评论', '2021-05-31 14:51:36', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:36', NULL);
INSERT INTO `t_sys_log` VALUES (403, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:51:37', 'GET', 'id=13&type=1', '200', '2017040329', '2021-05-31 14:51:36', NULL);
INSERT INTO `t_sys_log` VALUES (404, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:51:39', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:38', NULL);
INSERT INTO `t_sys_log` VALUES (405, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:51:39', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:39', NULL);
INSERT INTO `t_sys_log` VALUES (406, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:51:39', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:39', NULL);
INSERT INTO `t_sys_log` VALUES (407, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:51:44', 'GET', 'id=11&type=1', '200', '2017040329', '2021-05-31 14:51:44', NULL);
INSERT INTO `t_sys_log` VALUES (408, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 14:51:45', 'GET', 'id=11&userId=2017040329', '200', '2017040329', '2021-05-31 14:51:44', NULL);
INSERT INTO `t_sys_log` VALUES (409, 'http://localhost:8888/manage/score/add', '添加评分', '2021-05-31 14:51:47', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:47', NULL);
INSERT INTO `t_sys_log` VALUES (410, 'http://localhost:8888/manage/like/add', '添加点赞信息', '2021-05-31 14:51:49', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:48', NULL);
INSERT INTO `t_sys_log` VALUES (411, 'http://localhost:8888/manage/comment', '添加评论', '2021-05-31 14:51:54', 'POST', NULL, '200', '2017040329', '2021-05-31 14:51:53', NULL);
INSERT INTO `t_sys_log` VALUES (412, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:51:54', 'GET', 'id=11&type=1', '200', '2017040329', '2021-05-31 14:51:54', NULL);
INSERT INTO `t_sys_log` VALUES (413, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:51:56', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:55', NULL);
INSERT INTO `t_sys_log` VALUES (414, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:51:56', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:56', NULL);
INSERT INTO `t_sys_log` VALUES (415, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:51:56', 'GET', NULL, '200', '2017040329', '2021-05-31 14:51:56', NULL);
INSERT INTO `t_sys_log` VALUES (416, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:52:03', 'GET', 'id=10&type=1', '200', '2017040329', '2021-05-31 14:52:03', NULL);
INSERT INTO `t_sys_log` VALUES (417, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 14:52:03', 'GET', 'id=10&userId=2017040329', '200', '2017040329', '2021-05-31 14:52:03', NULL);
INSERT INTO `t_sys_log` VALUES (418, 'http://localhost:8888/manage/score/add', '添加评分', '2021-05-31 14:52:05', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:05', NULL);
INSERT INTO `t_sys_log` VALUES (419, 'http://localhost:8888/manage/like/add', '添加点赞信息', '2021-05-31 14:52:07', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:06', NULL);
INSERT INTO `t_sys_log` VALUES (420, 'http://localhost:8888/manage/comment', '添加评论', '2021-05-31 14:52:09', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:09', NULL);
INSERT INTO `t_sys_log` VALUES (421, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:52:10', 'GET', 'id=10&type=1', '200', '2017040329', '2021-05-31 14:52:09', NULL);
INSERT INTO `t_sys_log` VALUES (422, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:52:11', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:11', NULL);
INSERT INTO `t_sys_log` VALUES (423, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:52:11', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:11', NULL);
INSERT INTO `t_sys_log` VALUES (424, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:52:11', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:11', NULL);
INSERT INTO `t_sys_log` VALUES (425, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:52:15', 'GET', 'id=7&type=1', '200', '2017040329', '2021-05-31 14:52:15', NULL);
INSERT INTO `t_sys_log` VALUES (426, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 14:52:15', 'GET', 'id=7&userId=2017040329', '200', '2017040329', '2021-05-31 14:52:15', NULL);
INSERT INTO `t_sys_log` VALUES (427, 'http://localhost:8888/manage/score/add', '添加评分', '2021-05-31 14:52:18', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:18', NULL);
INSERT INTO `t_sys_log` VALUES (428, 'http://localhost:8888/manage/like/add', '添加点赞信息', '2021-05-31 14:52:20', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:19', NULL);
INSERT INTO `t_sys_log` VALUES (429, 'http://localhost:8888/manage/comment', '添加评论', '2021-05-31 14:52:24', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:23', NULL);
INSERT INTO `t_sys_log` VALUES (430, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:52:24', 'GET', 'id=7&type=1', '200', '2017040329', '2021-05-31 14:52:24', NULL);
INSERT INTO `t_sys_log` VALUES (431, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:52:25', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:25', NULL);
INSERT INTO `t_sys_log` VALUES (432, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:52:25', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:25', NULL);
INSERT INTO `t_sys_log` VALUES (433, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:52:25', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:25', NULL);
INSERT INTO `t_sys_log` VALUES (434, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:52:28', 'GET', 'id=12&type=1', '200', '2017040329', '2021-05-31 14:52:27', NULL);
INSERT INTO `t_sys_log` VALUES (435, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 14:52:28', 'GET', 'id=12&userId=2017040329', '200', '2017040329', '2021-05-31 14:52:28', NULL);
INSERT INTO `t_sys_log` VALUES (436, 'http://localhost:8888/manage/score/add', '添加评分', '2021-05-31 14:52:30', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:29', NULL);
INSERT INTO `t_sys_log` VALUES (437, 'http://localhost:8888/manage/like/add', '添加点赞信息', '2021-05-31 14:52:31', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:31', NULL);
INSERT INTO `t_sys_log` VALUES (438, 'http://localhost:8888/manage/comment', '添加评论', '2021-05-31 14:52:36', 'POST', NULL, '200', '2017040329', '2021-05-31 14:52:36', NULL);
INSERT INTO `t_sys_log` VALUES (439, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:52:37', 'GET', 'id=12&type=1', '200', '2017040329', '2021-05-31 14:52:36', NULL);
INSERT INTO `t_sys_log` VALUES (440, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:52:38', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:38', NULL);
INSERT INTO `t_sys_log` VALUES (441, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:52:39', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:38', NULL);
INSERT INTO `t_sys_log` VALUES (442, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:52:39', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:38', NULL);
INSERT INTO `t_sys_log` VALUES (443, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:52:53', 'GET', 'id=12&type=1', '200', '2017040329', '2021-05-31 14:52:52', NULL);
INSERT INTO `t_sys_log` VALUES (444, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 14:52:53', 'GET', 'id=12&userId=2017040329', '200', '2017040329', '2021-05-31 14:52:53', NULL);
INSERT INTO `t_sys_log` VALUES (445, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:52:54', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:53', NULL);
INSERT INTO `t_sys_log` VALUES (446, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:52:54', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:54', NULL);
INSERT INTO `t_sys_log` VALUES (447, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:52:54', 'GET', NULL, '200', '2017040329', '2021-05-31 14:52:54', NULL);
INSERT INTO `t_sys_log` VALUES (448, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:53:11', 'GET', NULL, '200', '2017040329', '2021-05-31 14:53:10', NULL);
INSERT INTO `t_sys_log` VALUES (449, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:53:11', 'GET', NULL, '200', '2017040329', '2021-05-31 14:53:10', NULL);
INSERT INTO `t_sys_log` VALUES (450, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:53:11', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:53:10', NULL);
INSERT INTO `t_sys_log` VALUES (451, 'http://localhost:8888/manage/classify/member', '为文献添加类别', '2021-05-31 14:53:18', 'POST', NULL, '200', '2017040329', '2021-05-31 14:53:17', NULL);
INSERT INTO `t_sys_log` VALUES (452, 'http://localhost:8888/manage/classify/member', '为文献添加类别', '2021-05-31 14:53:23', 'POST', NULL, '200', '2017040329', '2021-05-31 14:53:22', NULL);
INSERT INTO `t_sys_log` VALUES (453, 'http://localhost:8888/manage/classify/member', '为文献添加类别', '2021-05-31 14:53:25', 'POST', NULL, '200', '2017040329', '2021-05-31 14:53:24', NULL);
INSERT INTO `t_sys_log` VALUES (454, 'http://localhost:8888/manage/label/member', '为文献添加标签', '2021-05-31 14:53:31', 'POST', NULL, '200', '2017040329', '2021-05-31 14:53:31', NULL);
INSERT INTO `t_sys_log` VALUES (455, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:54:16', 'GET', NULL, '200', '2017040329', '2021-05-31 14:54:16', NULL);
INSERT INTO `t_sys_log` VALUES (456, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-05-31 14:54:16', 'GET', 'userId=2017040329', '200', '2017040329', '2021-05-31 14:54:16', NULL);
INSERT INTO `t_sys_log` VALUES (457, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:54:16', 'GET', NULL, '200', '2017040329', '2021-05-31 14:54:16', NULL);
INSERT INTO `t_sys_log` VALUES (458, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:54:42', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 14:54:41', NULL);
INSERT INTO `t_sys_log` VALUES (459, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:54:42', 'GET', 'role=admin', '200', '2017040330', '2021-05-31 14:54:41', NULL);
INSERT INTO `t_sys_log` VALUES (460, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:54:48', 'GET', NULL, '200', '2017040330', '2021-05-31 14:54:48', NULL);
INSERT INTO `t_sys_log` VALUES (461, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:54:48', 'GET', NULL, '200', '2017040330', '2021-05-31 14:54:48', NULL);
INSERT INTO `t_sys_log` VALUES (462, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:54:48', 'GET', NULL, '200', '2017040330', '2021-05-31 14:54:48', NULL);
INSERT INTO `t_sys_log` VALUES (463, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 14:54:53', 'GET', 'id=12&type=1', '200', '2017040330', '2021-05-31 14:54:53', NULL);
INSERT INTO `t_sys_log` VALUES (464, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 14:54:53', 'GET', 'id=12&userId=2017040330', '200', '2017040330', '2021-05-31 14:54:53', NULL);
INSERT INTO `t_sys_log` VALUES (465, 'http://localhost:8888/manage/like/add', '添加点赞信息', '2021-05-31 14:54:58', 'POST', NULL, '200', '2017040330', '2021-05-31 14:54:58', NULL);
INSERT INTO `t_sys_log` VALUES (466, 'http://localhost:8888/manage/score/add', '添加评分', '2021-05-31 14:55:06', 'POST', NULL, '200', '2017040330', '2021-05-31 14:55:06', NULL);
INSERT INTO `t_sys_log` VALUES (467, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 14:55:10', 'GET', NULL, '200', '2017040330', '2021-05-31 14:55:09', NULL);
INSERT INTO `t_sys_log` VALUES (468, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 14:55:10', 'GET', NULL, '200', '2017040330', '2021-05-31 14:55:09', NULL);
INSERT INTO `t_sys_log` VALUES (469, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 14:55:10', 'GET', NULL, '200', '2017040330', '2021-05-31 14:55:09', NULL);
INSERT INTO `t_sys_log` VALUES (470, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-31 14:55:22', 'GET', NULL, '200', '2017040330', '2021-05-31 14:55:22', NULL);
INSERT INTO `t_sys_log` VALUES (471, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-05-31 14:58:49', 'GET', 'role=admin', '200', '2017040330', '2021-05-31 14:58:48', NULL);
INSERT INTO `t_sys_log` VALUES (472, 'http://localhost:8888/manage/systemlogs', '获取所有日志', '2021-05-31 14:58:49', 'GET', NULL, '200', '2017040330', '2021-05-31 14:58:48', NULL);
INSERT INTO `t_sys_log` VALUES (473, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-05-31 14:58:56', 'GET', 'userId=2017040330', '200', '2017040330', '2021-05-31 14:58:56', NULL);
INSERT INTO `t_sys_log` VALUES (474, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 15:15:46', 'GET', NULL, '200', '2017040330', '2021-05-31 15:15:45', NULL);
INSERT INTO `t_sys_log` VALUES (475, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 15:15:46', 'GET', NULL, '200', '2017040330', '2021-05-31 15:15:45', NULL);
INSERT INTO `t_sys_log` VALUES (476, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 15:15:46', 'GET', NULL, '200', '2017040330', '2021-05-31 15:15:46', NULL);
INSERT INTO `t_sys_log` VALUES (477, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 15:16:02', 'GET', NULL, '200', '2017040330', '2021-05-31 15:16:02', NULL);
INSERT INTO `t_sys_log` VALUES (478, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 15:16:02', 'GET', NULL, '200', '2017040330', '2021-05-31 15:16:02', NULL);
INSERT INTO `t_sys_log` VALUES (479, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 15:16:02', 'GET', NULL, '200', '2017040330', '2021-05-31 15:16:02', NULL);
INSERT INTO `t_sys_log` VALUES (480, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 15:16:19', 'GET', 'id=12&type=1', '200', '2017040330', '2021-05-31 15:16:19', NULL);
INSERT INTO `t_sys_log` VALUES (481, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 15:16:20', 'GET', 'id=12&userId=2017040330', '200', '2017040330', '2021-05-31 15:16:19', NULL);
INSERT INTO `t_sys_log` VALUES (482, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 15:17:28', 'GET', NULL, '200', '2017040330', '2021-05-31 15:17:27', NULL);
INSERT INTO `t_sys_log` VALUES (483, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 15:17:28', 'GET', NULL, '200', '2017040330', '2021-05-31 15:17:27', NULL);
INSERT INTO `t_sys_log` VALUES (484, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 15:17:28', 'GET', NULL, '200', '2017040330', '2021-05-31 15:17:27', NULL);
INSERT INTO `t_sys_log` VALUES (485, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 15:17:30', 'GET', NULL, '200', '2017040330', '2021-05-31 15:17:29', NULL);
INSERT INTO `t_sys_log` VALUES (486, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 15:17:30', 'GET', NULL, '200', '2017040330', '2021-05-31 15:17:29', NULL);
INSERT INTO `t_sys_log` VALUES (487, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 15:17:30', 'GET', NULL, '200', '2017040330', '2021-05-31 15:17:29', NULL);
INSERT INTO `t_sys_log` VALUES (488, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 15:18:42', 'GET', NULL, '200', '2017040330', '2021-05-31 15:18:42', NULL);
INSERT INTO `t_sys_log` VALUES (489, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 15:18:42', 'GET', NULL, '200', '2017040330', '2021-05-31 15:18:42', NULL);
INSERT INTO `t_sys_log` VALUES (490, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 15:18:42', 'GET', NULL, '200', '2017040330', '2021-05-31 15:18:42', NULL);
INSERT INTO `t_sys_log` VALUES (491, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-05-31 15:18:46', 'GET', 'id=11&type=1', '200', '2017040330', '2021-05-31 15:18:45', NULL);
INSERT INTO `t_sys_log` VALUES (492, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-05-31 15:18:46', 'GET', 'id=11&userId=2017040330', '200', '2017040330', '2021-05-31 15:18:46', NULL);
INSERT INTO `t_sys_log` VALUES (493, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-05-31 15:18:52', 'GET', NULL, '200', '2017040330', '2021-05-31 15:18:51', NULL);
INSERT INTO `t_sys_log` VALUES (494, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-05-31 15:18:52', 'GET', NULL, '200', '2017040330', '2021-05-31 15:18:51', NULL);
INSERT INTO `t_sys_log` VALUES (495, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-05-31 15:18:52', 'GET', NULL, '200', '2017040330', '2021-05-31 15:18:51', NULL);
INSERT INTO `t_sys_log` VALUES (496, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-06-01 17:09:36', 'GET', 'userId=2017040330', '200', '2017040330', '2021-06-01 17:09:35', NULL);
INSERT INTO `t_sys_log` VALUES (497, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-06-01 17:09:36', 'GET', 'role=admin', '200', '2017040330', '2021-06-01 17:09:35', NULL);
INSERT INTO `t_sys_log` VALUES (498, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-06-01 17:09:38', 'GET', NULL, '200', '2017040330', '2021-06-01 17:09:38', NULL);
INSERT INTO `t_sys_log` VALUES (499, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-06-01 17:09:39', 'GET', NULL, '200', '2017040330', '2021-06-01 17:09:39', NULL);
INSERT INTO `t_sys_log` VALUES (500, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-06-02 09:20:39', 'GET', 'role=admin', '200', '2017040330', '2021-06-02 09:20:39', NULL);
INSERT INTO `t_sys_log` VALUES (501, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-06-02 09:21:06', 'GET', 'role=admin', '200', '2017040330', '2021-06-02 09:21:06', NULL);
INSERT INTO `t_sys_log` VALUES (502, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-06-02 09:21:07', 'GET', 'userId=2017040330', '200', '2017040330', '2021-06-02 09:21:06', NULL);
INSERT INTO `t_sys_log` VALUES (503, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-06-02 09:21:10', 'GET', NULL, '200', '2017040330', '2021-06-02 09:21:09', NULL);
INSERT INTO `t_sys_log` VALUES (504, 'http://localhost:8888/manage/user/role', '获取用户角色', '2021-06-02 09:21:11', 'GET', NULL, '200', '2017040330', '2021-06-02 09:21:10', NULL);
INSERT INTO `t_sys_log` VALUES (505, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-06-02 09:21:31', 'GET', 'role=common', '200', '2017040329', '2021-06-02 09:21:31', NULL);
INSERT INTO `t_sys_log` VALUES (506, 'http://localhost:8888/manage/getUserByUserId', '根据用户账号查询用户', '2021-06-02 09:21:31', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 09:21:31', NULL);
INSERT INTO `t_sys_log` VALUES (507, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-06-02 09:21:36', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 09:21:36', NULL);
INSERT INTO `t_sys_log` VALUES (508, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-06-02 09:21:36', 'GET', NULL, '200', '2017040329', '2021-06-02 09:21:36', NULL);
INSERT INTO `t_sys_log` VALUES (509, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-06-02 09:21:36', 'GET', NULL, '200', '2017040329', '2021-06-02 09:21:36', NULL);
INSERT INTO `t_sys_log` VALUES (510, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-06-02 09:21:37', 'GET', NULL, '200', '2017040329', '2021-06-02 09:21:37', NULL);
INSERT INTO `t_sys_log` VALUES (511, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-06-02 09:21:38', 'GET', NULL, '200', '2017040329', '2021-06-02 09:21:38', NULL);
INSERT INTO `t_sys_log` VALUES (512, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-06-02 09:21:38', 'GET', NULL, '200', '2017040329', '2021-06-02 09:21:38', NULL);
INSERT INTO `t_sys_log` VALUES (513, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-06-02 09:55:16', 'GET', NULL, '200', '2017040329', '2021-06-02 09:55:15', NULL);
INSERT INTO `t_sys_log` VALUES (514, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-06-02 09:55:16', 'GET', NULL, '200', '2017040329', '2021-06-02 09:55:15', NULL);
INSERT INTO `t_sys_log` VALUES (515, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-06-02 09:55:16', 'GET', NULL, '200', '2017040329', '2021-06-02 09:55:15', NULL);
INSERT INTO `t_sys_log` VALUES (516, 'http://localhost:8888/manage/menus', '获取所有菜单', '2021-06-02 09:55:17', 'GET', 'role=common', '200', '2017040329', '2021-06-02 09:55:17', NULL);
INSERT INTO `t_sys_log` VALUES (517, 'http://localhost:8888/manage/comment/byTopicId', '根据主题获取评论', '2021-06-02 10:44:20', 'GET', 'id=7&type=1', '200', '2017040329', '2021-06-02 10:44:19', NULL);
INSERT INTO `t_sys_log` VALUES (518, 'http://localhost:8888/manage/document/detail', '获取文献详情', '2021-06-02 10:44:21', 'GET', 'id=7&userId=2017040329', '200', '2017040329', '2021-06-02 10:44:20', NULL);
INSERT INTO `t_sys_log` VALUES (519, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-06-02 10:44:34', 'GET', NULL, '200', '2017040329', '2021-06-02 10:44:33', NULL);
INSERT INTO `t_sys_log` VALUES (520, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-06-02 10:44:34', 'GET', NULL, '200', '2017040329', '2021-06-02 10:44:33', NULL);
INSERT INTO `t_sys_log` VALUES (521, 'http://localhost:8888/manage/meetings', '获取所有会议', '2021-06-02 10:44:36', 'GET', NULL, '200', '2017040329', '2021-06-02 10:44:35', NULL);
INSERT INTO `t_sys_log` VALUES (522, 'http://localhost:8888/manage/reports', '获取所有日报周报', '2021-06-02 10:44:38', 'GET', NULL, '200', '2017040329', '2021-06-02 10:44:37', NULL);
INSERT INTO `t_sys_log` VALUES (523, 'http://localhost:8888/manage/report/id', '根据报告id查询报告信息', '2021-06-02 10:44:41', 'GET', 'id=1', '200', '2017040329', '2021-06-02 10:44:40', NULL);
INSERT INTO `t_sys_log` VALUES (524, 'http://localhost:8888/manage/softwares/userId', '通过用户账户获取软件信息', '2021-06-02 10:44:45', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:44:45', NULL);
INSERT INTO `t_sys_log` VALUES (525, 'http://localhost:8888/manage/softwares', '获取所有软件信息', '2021-06-02 10:44:53', 'GET', NULL, '200', '2017040329', '2021-06-02 10:44:52', NULL);
INSERT INTO `t_sys_log` VALUES (526, 'http://localhost:8888/manage/achievement/byUploadUser', '通过上传者id获取成果', '2021-06-02 10:44:57', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:44:56', NULL);
INSERT INTO `t_sys_log` VALUES (527, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-06-02 10:45:01', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:01', NULL);
INSERT INTO `t_sys_log` VALUES (528, 'http://localhost:8888/manage/teams/userId', '通过用户账号获取团队', '2021-06-02 10:45:06', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:45:05', NULL);
INSERT INTO `t_sys_log` VALUES (529, 'http://localhost:8888/manage/teams', '获取所有团队', '2021-06-02 10:45:08', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:07', NULL);
INSERT INTO `t_sys_log` VALUES (530, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-06-02 10:45:14', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:14', NULL);
INSERT INTO `t_sys_log` VALUES (531, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-06-02 10:45:14', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:14', NULL);
INSERT INTO `t_sys_log` VALUES (532, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-06-02 10:45:14', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:14', NULL);
INSERT INTO `t_sys_log` VALUES (533, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-06-02 10:45:15', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:45:14', NULL);
INSERT INTO `t_sys_log` VALUES (534, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-06-02 10:45:19', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:18', NULL);
INSERT INTO `t_sys_log` VALUES (535, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-06-02 10:45:19', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:18', NULL);
INSERT INTO `t_sys_log` VALUES (536, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-06-02 10:45:19', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:18', NULL);
INSERT INTO `t_sys_log` VALUES (537, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-06-02 10:45:19', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:19', NULL);
INSERT INTO `t_sys_log` VALUES (538, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-06-02 10:45:19', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:45:19', NULL);
INSERT INTO `t_sys_log` VALUES (539, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-06-02 10:45:19', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:19', NULL);
INSERT INTO `t_sys_log` VALUES (540, 'http://localhost:8888/manage/label/all/byUserId', '通过用户id查询标签', '2021-06-02 10:45:21', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:45:20', NULL);
INSERT INTO `t_sys_log` VALUES (541, 'http://localhost:8888/manage/classify/all/byUserId', '通过用户账号获取类别信息', '2021-06-02 10:45:22', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:45:21', NULL);
INSERT INTO `t_sys_log` VALUES (542, 'http://localhost:8888/manage/class', '获取所有课表', '2021-06-02 10:45:35', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:45:35', NULL);
INSERT INTO `t_sys_log` VALUES (543, 'http://localhost:8888/manage/assets', '获取所有资产', '2021-06-02 10:45:38', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:37', NULL);
INSERT INTO `t_sys_log` VALUES (544, 'http://localhost:8888/manage/class', '获取所有课表', '2021-06-02 10:45:39', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:45:38', NULL);
INSERT INTO `t_sys_log` VALUES (545, 'http://localhost:8888/manage/documents/byUserId', '通过用户账号获取文献', '2021-06-02 10:45:55', 'GET', 'userId=2017040329', '200', '2017040329', '2021-06-02 10:45:55', NULL);
INSERT INTO `t_sys_log` VALUES (546, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-06-02 10:45:55', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:55', NULL);
INSERT INTO `t_sys_log` VALUES (547, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-06-02 10:45:56', 'GET', NULL, '200', '2017040329', '2021-06-02 10:45:56', NULL);
INSERT INTO `t_sys_log` VALUES (548, 'http://localhost:8888/manage/label/all', '获取所有标签', '2021-06-02 10:46:06', 'GET', NULL, '200', '2017040329', '2021-06-02 10:46:06', NULL);
INSERT INTO `t_sys_log` VALUES (549, 'http://localhost:8888/manage/documents', '获取所有文献', '2021-06-02 10:46:06', 'GET', NULL, '200', '2017040329', '2021-06-02 10:46:06', NULL);
INSERT INTO `t_sys_log` VALUES (550, 'http://localhost:8888/manage/classify/all', '获取所有分类', '2021-06-02 10:46:06', 'GET', NULL, '200', '2017040329', '2021-06-02 10:46:06', NULL);
INSERT INTO `t_sys_log` VALUES (551, 'http://localhost:8888/manage/tasks', '获取所有任务', '2021-06-02 10:46:07', 'GET', NULL, '200', '2017040329', '2021-06-02 10:46:07', NULL);
INSERT INTO `t_sys_log` VALUES (552, 'http://localhost:8888/manage/users', '获取所有用户信息', '2021-06-02 10:46:08', 'GET', NULL, '200', '2017040329', '2021-06-02 10:46:07', NULL);

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `publish_user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '发布者',
  `start_time` date NOT NULL COMMENT '任务开始时间',
  `end_time` date NOT NULL COMMENT '任务结束时间',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任务内容',
  `state` tinyint(1) NOT NULL COMMENT '任务状态: 0未完成 1正在进行 2已完成',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES (1, '2017040330', '2021-05-04', '2021-05-15', '完成毕设', 0, '2021-05-13 22:40:05');
INSERT INTO `t_task` VALUES (2, '2017040330', '2021-05-04', '2021-05-14', '写毕业论文', 1, '2021-05-14 13:06:44');

-- ----------------------------
-- Table structure for t_task_member
-- ----------------------------
DROP TABLE IF EXISTS `t_task_member`;
CREATE TABLE `t_task_member`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(0) NOT NULL,
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `task_user_unique_index`(`task_id`, `user_id`) USING BTREE COMMENT '每个任务成员不重复'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_task_member
-- ----------------------------
INSERT INTO `t_task_member` VALUES (2, 1, '2017040328');
INSERT INTO `t_task_member` VALUES (3, 1, '2017040329');
INSERT INTO `t_task_member` VALUES (5, 2, '2017040328');
INSERT INTO `t_task_member` VALUES (6, 2, '2017040329');
INSERT INTO `t_task_member` VALUES (7, 2, '2017040330');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录账号(学号)',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `pic_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像存储路径',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '姓名',
  `identity` tinyint(1) NOT NULL COMMENT '身份(1 大一; 2 大二; 3 大三; 4大四; 5 研一; 6 研二; 7 研三 0 教师)',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否被删除: 1:是 0 否',
  `sex` tinyint(1) NOT NULL COMMENT '性别：1：男，0：女',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '手机号',
  `qq_open_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'QQ id',
  `wx_open_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '微信 id',
  `birth` date NULL DEFAULT NULL COMMENT '出生日期',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `login_count` bigint(0) NOT NULL DEFAULT 0 COMMENT '登录次数',
  `create_uid` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '创建者id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id_password_index`(`user_id`, `password`) USING BTREE COMMENT '学号唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '2017040330', '123456', '/resource/img/16216761833846f6f68a0234cfc14!400x400_big.jpg', '张俊峰', 4, 0, 1, '2017040330@mail.buct.edu.cn', '13120456266', '869830837', 'imzhangjunfeng', '2021-05-04', '2021-06-01 17:09:33', '2021-06-02 09:21:06', 17, '2017040330', '2021-04-09 14:29:50', '2021-06-02 09:21:05');
INSERT INTO `t_user` VALUES (2, '2017040329', '123456', '/resource/img/1621676579565f5f4f1e116c280fa!400x400_big.jpg', '李雨洋', 4, 0, 1, '2017040329@mail.buct.edu.cn', '15524763829', '123456', 'liyuyang', '2017-04-06', '2021-05-31 14:05:35', '2021-06-02 09:21:30', 5, '2017040330', '2021-04-10 19:01:00', '2021-06-02 09:21:29');
INSERT INTO `t_user` VALUES (3, '2017040328', 'zzf123456', '/resource/img/user.jpg', '张正发', 4, 0, 1, '2017040328@mail.buct.edu.cn', '18810243395', '', '', '1997-08-05', '2021-05-22 19:03:23', '2021-05-22 19:09:04', 1, '2017040328', '2021-04-13 20:09:10', '2021-05-22 19:09:32');
INSERT INTO `t_user` VALUES (6, '2017040334', 'lw123456', '/resource/img/user.jpg', '罗威', 4, 0, 1, '2017040334@mail.buct.edu.cn', '13120456266', NULL, NULL, '2021-05-05', NULL, '2021-05-22 19:03:23', 0, '2017040330', '2021-05-16 15:02:20', '2021-05-22 19:03:50');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户id',
  `role_id` bigint(0) NOT NULL COMMENT '角色 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, '2017040330', 1);
INSERT INTO `t_user_role` VALUES (3, '2017040329', 2);
INSERT INTO `t_user_role` VALUES (6, '2017040328', 2);
INSERT INTO `t_user_role` VALUES (10, '2017040334', 2);

-- ----------------------------
-- Procedure structure for role_menu_insert
-- ----------------------------
DROP PROCEDURE IF EXISTS `role_menu_insert`;
delimiter ;;
CREATE PROCEDURE `role_menu_insert`()
BEGIN
	#Routine body goes here...
DECLARE i INT DEFAULT 2;
WHILE i <= 22 DO
	INSERT INTO t_role_menu(role_id, menu_id) VALUES(2, i);
	SET i = i + 1;
END WHILE;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
