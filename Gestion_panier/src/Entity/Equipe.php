<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Equipe
 *
 * @ORM\Table(name="equipe")
 * @ORM\Entity
 */
class Equipe
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_equipe", type="string", length=30, nullable=false)
     */
    private $nomEquipe;

    /**
     * @var int
     *
     * @ORM\Column(name="membres", type="integer", nullable=false)
     */
    private $membres;

    /**
     * @var int
     *
     * @ORM\Column(name="pts_exp", type="integer", nullable=false)
     */
    private $ptsExp;

    /**
     * @var int
     *
     * @ORM\Column(name="tournois_gagne", type="integer", nullable=false)
     */
    private $tournoisGagne;


}
