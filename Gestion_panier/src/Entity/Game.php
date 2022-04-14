<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Game
 *
 * @ORM\Table(name="game", uniqueConstraints={@ORM\UniqueConstraint(name="game_name", columns={"game_name"})})
 * @ORM\Entity
 */
class Game
{
    /**
     * @var int
     *
     * @ORM\Column(name="game_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $gameId;

    /**
     * @var string
     *
     * @ORM\Column(name="game_name", type="string", length=30, nullable=false)
     */
    private $gameName;

    /**
     * @var int
     *
     * @ORM\Column(name="category_id", type="integer", nullable=false)
     */
    private $categoryId;

    /**
     * @var string
     *
     * @ORM\Column(name="game_description", type="string", length=100, nullable=false)
     */
    private $gameDescription;

    /**
     * @var string
     *
     * @ORM\Column(name="game_link", type="string", length=100, nullable=false)
     */
    private $gameLink;

    /**
     * @var string
     *
     * @ORM\Column(name="game_vid", type="string", length=100, nullable=false)
     */
    private $gameVid;

    /**
     * @var string
     *
     * @ORM\Column(name="game_addDate", type="string", length=20, nullable=false)
     */
    private $gameAdddate;


}
