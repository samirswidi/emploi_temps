-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 06 déc. 2023 à 19:46
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `emploi_temps`
--

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

CREATE TABLE `classe` (
  `id_classe` int(11) NOT NULL,
  `libelle_classe` varchar(100) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id_classe`, `libelle_classe`, `description`) VALUES
(1, '6 eme', '6 eme'),
(2, '4 eme', '4 eme'),
(3, '5 eme', '5eme'),
(19, 'MP2L', 'MP2L');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `matricule` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`matricule`, `nom`, `contact`) VALUES
('8', '8', '8'),
('9', '9', '9'),
('java_bahroun', 'Bahroun Sahbi', '+21687654321'),
('mat1', 'nom1', 'contact1'),
('mat2', 'nom2', 'contact3'),
('mat3', 'mat3', 'con3'),
('mattest', 'samirm', '+21639876524');

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

CREATE TABLE `seance` (
  `id_seance` int(11) NOT NULL,
  `classe` varchar(100) NOT NULL,
  `matiere` varchar(100) NOT NULL,
  `jour` varchar(20) NOT NULL,
  `heure` varchar(100) NOT NULL,
  `matricule` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`id_seance`, `classe`, `matiere`, `jour`, `heure`, `matricule`) VALUES
(1, '4 eme', 'svt', 'Lundi', '08:00 & 08:30', '10'),
(2, '4 eme', 'math', 'Lundi', '09:00 & 10:00', '9'),
(14, '6 eme', 'java', 'Lundi', '09:00 & 09:00', 'mat1'),
(16, '6 eme', 'web', 'Lundi', '10:00 & 10:30', 'mat1'),
(17, '6 eme', 'java', 'Mardi', '10:00 & 10:30', 'mat1'),
(18, '6 eme', 'BDA', 'Mercredi', '10:00 & 10:30', 'mat1'),
(19, '6 eme', 'Réseau', 'Jeudi', '10:00 & 10:30', 'mat1'),
(20, '6 eme', 'Prog système réseau', 'Vendredi', '10:00 & 10:30', 'mat1'),
(21, '6 eme', 'Méthodologie', 'Samedi', '10:00 & 10:30', 'mat1'),
(22, 'MP2L', 'java', 'Samedi', '11:00 & 13:00', 'java_bahroun');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `user`, `pwd`) VALUES
(1, 'user', 'pwd');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`id_classe`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`matricule`);

--
-- Index pour la table `seance`
--
ALTER TABLE `seance`
  ADD PRIMARY KEY (`id_seance`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `classe`
--
ALTER TABLE `classe`
  MODIFY `id_classe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `seance`
--
ALTER TABLE `seance`
  MODIFY `id_seance` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
