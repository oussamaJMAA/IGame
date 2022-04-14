<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Rating
 *
 * @ORM\Table(name="rating", indexes={@ORM\Index(name="fk_client", columns={"client_id"}), @ORM\Index(name="fk_game_id", columns={"game_id"})})
 * @ORM\Entity
 */
class Rating
{
    /**
     * @var int
     *
     * @ORM\Column(name="rating_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $ratingId;

    /**
     * @var int
     *
     * @ORM\Column(name="game_id", type="integer", nullable=false)
     */
    private $gameId;

    /**
     * @var int
     *
     * @ORM\Column(name="client_id", type="integer", nullable=false)
     */
    private $clientId;

    /**
     * @var int
     *
     * @ORM\Column(name="rating_value", type="integer", nullable=false)
     */
    private $ratingValue;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="rating_date", type="date", nullable=true)
     */
    private $ratingDate;


}
