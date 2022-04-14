<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Participation
 *
 * @ORM\Table(name="participation", indexes={@ORM\Index(name="fk_e", columns={"id_equipe"}), @ORM\Index(name="fk_t", columns={"id_tournois"})})
 * @ORM\Entity
 */
class Participation
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
     * @var int|null
     *
     * @ORM\Column(name="id_equipe", type="integer", nullable=true)
     */
    private $idEquipe;

    /**
     * @var int|null
     *
     * @ORM\Column(name="id_tournois", type="integer", nullable=true)
     */
    private $idTournois;


}
