<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Game
 *
 * @ORM\Table(name="game", indexes={@ORM\Index(name="FK_category_id", columns={"idcategory"})})
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
     *     @Assert\NotBlank(message="veuillez saisir le nom du jeu")
     *     @Assert\Length(
     *     min=6,
     *     minMessage = "le nom du jeu doit etre supérieur a 4 caracteres",
     *     allowEmptyString = false
     *     )
     * @ORM\Column(name="game_name", type="string", length=255, nullable=false)
     */
    private $gameName;

    /**
     * @var string
     * @Assert\NotBlank(message="la description  est  obligatoire")
     * @ORM\Column(name="game_description", type="string", length=255, nullable=false)
     */
    private $gameDescription;

    /**
     * @var string
      * @Assert\NotBlank(message="le lien  est  obligatoire")
     * @ORM\Column(name="game_link", type="string", length=255, nullable=false)
     */
    private $gameLink;

    /**
     * @var string
    * @Assert\NotBlank(message="l'image est  obligatoire")
     * @ORM\Column(name="game_img", type="string", length=255, nullable=false)
     */
    private $gameImg;

    /**
     * @var \Category
     * @Assert\NotBlank(message="la category est  obligatoire")
     * @ORM\ManyToOne(targetEntity="Category")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idcategory", referencedColumnName="category_id")
     * })
     */
    private $idcategory;



    /**
     * Get the value of gameId
     *
     * @return  int
     */ 
    public function getGameId()
    {
        return $this->gameId;
    }

    /**
     * Set the value of gameId
     *
     * @param  int  $gameId
     *
     * @return  self
     */ 
    public function setGameId(int $gameId)
    {
        $this->gameId = $gameId;

        return $this;
    }

    /**
     * Get the value of gameName
     *
     * @return  string
     */ 
    public function getGameName()
    {
        return $this->gameName;
    }

    /**
     * Set the value of gameName
     *
     * @param  string  $gameName
     *
     * @return  self
     */ 
    public function setGameName(string $gameName)
    {
        $this->gameName = $gameName;

        return $this;
    }

    /**
     * Get the value of gameDescription
     *
     * @return  string
     */ 
    public function getGameDescription()
    {
        return $this->gameDescription;
    }

    /**
     * Set the value of gameDescription
     *
     * @param  string  $gameDescription
     *
     * @return  self
     */ 
    public function setGameDescription(string $gameDescription)
    {
        $this->gameDescription = $gameDescription;

        return $this;
    }
}
