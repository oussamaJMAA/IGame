<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Tournois
 *
 * @ORM\Table(name="tournois")
 * @ORM\Entity
 */
class Tournois
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
     * @ORM\Column(name="nom_tournois", type="string", length=20, nullable=false)
     */
    private $nomTournois;

    /**
     * @var int
     *
     * @ORM\Column(name="capacite", type="integer", nullable=false)
     */
    private $capacite;

    /**
     * @var string
     *
     * @ORM\Column(name="platforme", type="string", length=30, nullable=false)
     */
    private $platforme;

    /**
     * @var string
     *
     * @ORM\Column(name="recompense", type="string", length=30, nullable=false)
     */
    private $recompense;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_tournois", type="date", nullable=true)
     */
    private $dateTournois;


}
