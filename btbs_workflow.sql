/*
 Navicat Premium Data Transfer

 Source Server         : 47.96.89.84
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 47.96.89.84:3306
 Source Schema         : btbs_workflow

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 28/08/2019 14:52:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wf_re_deployment
-- ----------------------------
DROP TABLE IF EXISTS `wf_re_deployment`;
CREATE TABLE `wf_re_deployment`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属id',
  `wf_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程类型',
  `step_num` int(11) NULL DEFAULT NULL COMMENT '总步骤数目',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程自定义主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wf_re_procdef
-- ----------------------------
DROP TABLE IF EXISTS `wf_re_procdef`;
CREATE TABLE `wf_re_procdef`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属id',
  `wf_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程类型',
  `re_deploy_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主表id',
  `step_current` int(11) NULL DEFAULT NULL COMMENT '当前步骤',
  `express_type` int(11) NULL DEFAULT NULL COMMENT '表达式类型',
  `express_keys` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表达式键',
  `operate_type` int(11) NULL DEFAULT NULL COMMENT '操作类型',
  `operator_type` int(11) NULL DEFAULT NULL COMMENT '操作方类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程自定义信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wf_ru_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_ru_task`;
CREATE TABLE `wf_ru_task`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属id',
  `re_deploy_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程自定义主表ID',
  `re_procdef_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程自定义信息表ID',
  `current_step` int(11) NULL DEFAULT NULL COMMENT '当前步骤',
  `biz_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务ID',
  `wf_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程类型',
  `status` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `first_step_re_procdef_id` int(11) NULL DEFAULT NULL,
  `pre_step_re_procdef_id` int(11) NULL DEFAULT NULL,
  `current_step_re_procdef_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '当前任务表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
