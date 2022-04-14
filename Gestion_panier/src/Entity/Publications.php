<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Publications
 *
 * @ORM\Table(name="publications")
 * @ORM\Entity
 */
class Publications
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_pub", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPub;

    /**
     * @var string
     *
     * @ORM\Column(name="titre_pub", type="string", length=20, nullable=false)
     */
    private $titrePub;

    /**
     * @var string
     *
     * @ORM\Column(name="description_pub", type="text", length=65535, nullable=false)
     */
    private $descriptionPub;

    /**
     * @var int|null
     *
     * @ORM\Column(name="id_commentaire", type="integer", nullable=true)
     */
    private $idCommentaire;


}
