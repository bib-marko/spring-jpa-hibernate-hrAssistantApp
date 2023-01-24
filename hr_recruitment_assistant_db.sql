-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2023 at 07:37 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hr_recruitment_assistant_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE `candidate` (
  `id` bigint(20) NOT NULL,
  `contact_number` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `date_endorsed` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `hiring_manager` varchar(255) DEFAULT NULL,
  `interview_result` varchar(255) DEFAULT NULL,
  `is_rejection_email_sent` bit(1) DEFAULT NULL,
  `offer_date` datetime DEFAULT NULL,
  `offer_status` varchar(255) DEFAULT NULL,
  `on_boarding_date` datetime DEFAULT NULL,
  `overall_status` varchar(255) NOT NULL,
  `paper_screening_status` varchar(255) DEFAULT NULL,
  `position` varchar(255) NOT NULL,
  `tech_interview_schedule` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `candidate`
--

INSERT INTO `candidate` (`id`, `contact_number`, `created_at`, `date_endorsed`, `email`, `full_name`, `hiring_manager`, `interview_result`, `is_rejection_email_sent`, `offer_date`, `offer_status`, `on_boarding_date`, `overall_status`, `paper_screening_status`, `position`, `tech_interview_schedule`, `updated_at`) VALUES
(1, '+86 489 928 3724', '2021-09-11 00:00:00', '2022-05-10 00:00:00', 'hpenwarden0@cyberchimps.com', 'Marysa Agar', 'Herschel Penwarden', 'Passed', b'0', '2021-12-02 00:00:00', 'Declined', NULL, 'Under Assessment', 'Failed', 'Senior Java Developer', '2022-07-25 00:00:00', '2022-06-07 00:00:00'),
(2, '+62 412 364 3714', '2022-04-20 00:00:00', '2021-09-26 00:00:00', 'bedgecombe1@google.ca', 'Winnah Woolens', 'Bing Edgecombe', 'Failed', b'1', '2021-08-19 00:00:00', 'Declined', NULL, 'Passed - To Hire', 'Passed', 'Senior QA Tester', '2022-01-27 00:00:00', '2022-07-22 00:00:00'),
(3, '+225 561 423 8246', '2022-05-04 00:00:00', '2021-10-17 00:00:00', 'lstanlick2@exblog.jp', 'Aliza Westcot', 'Libby Stanlick', 'Passed', b'1', '2022-03-22 00:00:00', 'Declined', NULL, 'Hired', 'Passed', 'Web Developer', '2022-03-13 00:00:00', '2022-04-18 00:00:00'),
(4, '+33 250 946 1141', '2022-02-13 00:00:00', '2022-01-01 00:00:00', 'mclemencon3@pen.io', 'Sauncho Gilmartin', 'Margarete Clemencon', 'Passed', b'1', '2022-03-10 00:00:00', 'Declined', NULL, 'Hired', 'Passed', 'Junior Java Developer', '2021-12-22 00:00:00', '2021-10-20 00:00:00'),
(5, '+86 800 239 1297', '2021-09-23 00:00:00', '2022-02-17 00:00:00', 'abauer4@over-blog.com', 'Debera Doman', 'Alane Bauer', 'Passed', b'0', '2021-11-09 00:00:00', 'Declined', NULL, 'Passed - To Hire', 'Passed', 'Web Developer', '2022-01-18 00:00:00', '2021-09-18 00:00:00'),
(6, '+381 985 259 1266', '2022-07-20 00:00:00', '2022-05-09 00:00:00', 'lscotson5@usa.gov', 'Isidro Hambling', 'Lura Scotson', 'Failed', b'1', '2021-11-10 00:00:00', 'Declined', NULL, 'Passed - To Hire', 'Passed', 'Junior QA Tester', '2022-01-26 00:00:00', '2022-03-13 00:00:00'),
(7, '+86 589 126 1071', '2022-02-19 00:00:00', '2021-11-23 00:00:00', 'ypatek6@princeton.edu', 'Corbin Ladley', 'Yancey Patek', 'Failed', b'0', '2022-06-01 00:00:00', 'Accepted', NULL, 'Pool', 'Passed', 'Web Developer', '2022-06-21 00:00:00', '2021-09-29 00:00:00'),
(8, '+46 914 398 9376', '2022-06-13 00:00:00', '2022-01-16 00:00:00', 'mpalmer7@virginia.edu', 'Kirstyn Klimke', 'Michaeline Palmer', 'Passed', b'0', '2022-07-29 00:00:00', 'Accepted', NULL, 'Passed - To Hire', 'Passed', 'Junior Java Developer', '2022-05-30 00:00:00', '2022-05-04 00:00:00'),
(9, '+7 234 911 1995', '2022-02-22 00:00:00', '2022-03-07 00:00:00', 'atanner8@jimdo.com', 'Scarlet Markham', 'Alasdair Tanner', 'Failed', b'0', '2022-05-30 00:00:00', 'Declined', NULL, 'Pool', 'Passed', 'Junior QA Tester', '2021-11-19 00:00:00', '2021-11-17 00:00:00'),
(10, '+66 139 613 7762', '2022-02-14 00:00:00', '2022-03-28 00:00:00', 'agoodright9@google.nl', 'Cynthy Waistall', 'Annamarie Goodright', 'Passed', b'1', '2022-02-19 00:00:00', 'Accepted', NULL, 'Hired', 'Passed', 'Senior Java Developer', '2021-11-17 00:00:00', '2021-10-19 00:00:00'),
(11, '+51 938 947 9950', '2022-06-26 00:00:00', '2021-10-23 00:00:00', 'bandreopolosa@is.gd', 'Henrie Pitts', 'Blanche Andreopolos', 'Failed', b'0', '2021-09-16 00:00:00', 'Declined', NULL, 'Failed - No Show', 'Passed', 'Senior Java Developer', '2021-11-20 00:00:00', '2021-10-24 00:00:00'),
(12, '+93 656 430 3821', '2022-05-10 00:00:00', '2021-08-27 00:00:00', 'smcboyleb@free.fr', 'Isaac Petti', 'Sherilyn McBoyle', 'Failed', b'0', '2022-05-05 00:00:00', 'Declined', NULL, 'Passed - Declined JO', 'Passed', 'Junior Java Developer', '2022-05-19 00:00:00', '2022-04-02 00:00:00'),
(13, '+93 156 990 1433', '2021-08-27 00:00:00', '2022-07-12 00:00:00', 'cricardoc@dagondesign.com', 'Calypso Dressell', 'Christal Ricardo', 'Failed', b'0', '2022-06-11 00:00:00', 'Accepted', NULL, 'Passed - Retract JO', 'Passed', 'Senior Java Developer', '2021-12-24 00:00:00', '2021-08-04 00:00:00'),
(14, '+298 963 709 6194', '2021-09-01 00:00:00', '2022-03-22 00:00:00', 'jgaymerd@yahoo.co.jp', 'Kristo Monahan', 'Josey Gaymer', 'Failed', b'0', '2021-12-21 00:00:00', 'Accepted', NULL, 'Passed - Retract JO', 'Passed', 'Junior Java Developer', '2022-04-27 00:00:00', '2021-08-12 00:00:00'),
(15, '+7 638 321 2028', '2022-07-19 00:00:00', '2021-12-04 00:00:00', 'mlocknere@reddit.com', 'Jamaal Ferencz', 'Mirna Lockner', 'Failed', b'1', '2022-02-08 00:00:00', 'Accepted', NULL, 'Passed - Retract JO', 'Passed', 'Web Developer', '2021-11-26 00:00:00', '2022-05-24 00:00:00'),
(16, '+33 605 936 1405', '2021-10-07 00:00:00', '2021-11-02 00:00:00', 'imeakingf@unblog.fr', 'Dre Ferrino', 'Iolande Meaking', 'Failed', b'0', '2021-08-07 00:00:00', 'Accepted', NULL, 'Passed - Retract JO', 'Failed', 'Junior Java Developer', '2022-07-12 00:00:00', '2022-02-20 00:00:00'),
(17, '+86 692 869 7927', '2022-05-30 00:00:00', '2022-01-12 00:00:00', 'ggiloglyg@google.es', 'Linnell Cantwell', 'Gilemette Gilogly', 'Failed', b'0', '2021-08-02 00:00:00', 'Accepted', NULL, 'Failed - No Show', 'Failed', 'Junior QA Tester', '2022-01-04 00:00:00', '2021-08-22 00:00:00'),
(18, '+46 367 193 5065', '2021-12-22 00:00:00', '2022-06-10 00:00:00', 'cnolih@google.co.uk', 'Heidi Poller', 'Carlina Noli', 'Passed', b'0', '2021-08-11 00:00:00', 'Accepted', NULL, 'Hired', 'Failed', 'Web Developer', '2022-04-12 00:00:00', '2021-11-27 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `disclaimer` mediumtext DEFAULT NULL,
  `information` mediumtext DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `email_report`
--

CREATE TABLE `email_report` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `hr_incharge` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `template` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `email_report`
--

INSERT INTO `email_report` (`id`, `created_at`, `hr_incharge`, `subject`, `template`) VALUES
(6, '2022-08-09 16:03:50', 'Melody Jalali', 'My Subject', 'Corporate'),
(7, '2022-08-09 16:08:23', 'Melody Jalali', 'My Subject', 'Corporate'),
(8, '2022-08-15 10:39:34', 'Melody Jalali', 'My Subject', 'Corporate'),
(11, '2022-08-15 13:38:45', 'Melody Jalali', 'My Subject', 'Corporate'),
(12, '2022-08-15 13:39:58', 'Melody Jalali', 'My Subject', 'Corporate');

-- --------------------------------------------------------

--
-- Table structure for table `email_sent_history`
--

CREATE TABLE `email_sent_history` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `last_follow_update` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `email_report_id_fk` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `email_sent_history`
--

INSERT INTO `email_sent_history` (`id`, `created_at`, `email`, `full_name`, `last_follow_update`, `position`, `status`, `email_report_id_fk`) VALUES
(1, '2022-08-09 16:03:50', 'cricardoc@dagondesign.com', 'Calypso Dressell', '1month', 'Senior Java Developer', 'Passed - Retract JO', 6),
(2, '2022-08-09 16:08:23', 'bedgecombe1@google.ca', 'Winnah Woolens', '1month', 'Senior QA Tester', 'Passed - To Hire', 7),
(3, '2022-08-15 10:39:35', 'mlocknere@reddit.com', 'Jamaal Ferencz', '6month', 'Web Developer', 'Passed - Retract JO', 8),
(4, '2022-08-15 13:38:45', 'mlocknere@reddit.com', 'Jamaal Ferencz', '6month', 'Web Developer', 'Passed - Retract JO', 11),
(5, '2022-08-15 13:39:58', 'mlocknere@reddit.com', 'Jamaal Ferencz', '6month', 'Web Developer', 'Passed - Retract JO', 12);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `job`
--

CREATE TABLE `job` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `job_desc` varchar(255) DEFAULT NULL,
  `job_group` varchar(255) DEFAULT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `template`
--

CREATE TABLE `template` (
  `id` bigint(20) NOT NULL,
  `body` longtext NOT NULL,
  `created_at` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `template`
--

INSERT INTO `template` (`id`, `body`, `created_at`, `title`, `updated_at`) VALUES
(1, '<html>\r\n    <body>\r\n\r\n    <style>\r\n        body {\r\n        padding: 50px;\r\n        }\r\n        span {\r\n        display: inline-block;\r\n        background: #a0ffa0;\r\n        padding: 20px;\r\n        }\r\n    </style>\r\n\r\n    <span>\r\n        Edit the page on top, see \r\n        the result on the bottom.\r\n    </span>\r\n\r\n    </body>\r\n    </html>', '2022-08-09 09:12:51', 'Corporate', '2022-08-09 09:12:51');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `candidate`
--
ALTER TABLE `candidate`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_qfut8ruekode092nlkipgl09g` (`email`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `email_report`
--
ALTER TABLE `email_report`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `email_sent_history`
--
ALTER TABLE `email_sent_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK178nsxhrinw2jca8w5br5x8nm` (`email_report_id_fk`);

--
-- Indexes for table `job`
--
ALTER TABLE `job`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `template`
--
ALTER TABLE `template`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `candidate`
--
ALTER TABLE `candidate`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `email_report`
--
ALTER TABLE `email_report`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `email_sent_history`
--
ALTER TABLE `email_sent_history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `job`
--
ALTER TABLE `job`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `email_sent_history`
--
ALTER TABLE `email_sent_history`
  ADD CONSTRAINT `FK178nsxhrinw2jca8w5br5x8nm` FOREIGN KEY (`email_report_id_fk`) REFERENCES `email_report` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
