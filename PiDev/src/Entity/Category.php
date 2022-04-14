<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Category
 *
 * @ORM\Table(name="category", uniqueConstraints={@ORM\UniqueConstraint(name="category_name", columns={"category_name"})})
 * @ORM\Entity
 */
class Category
{
    /**
     * @var int
     *
     * @ORM\Column(name="category_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $categoryId;

    /**
     * @var int
     *    @Assert\NotBlank(message="veuillez saisir le nom du categorie")
     *     @Assert\Length(
     *     min=3,
     *     max=15,
     *     minMessage = "le nom du categorie doit etre supérieur a 4 caracteres",
     *     maxMessage  ="le nom du categorie ne doit pas depasser 15 caracteres",
     *     allowEmptyString = false
     *     )
     * @ORM\Column(name="category_name", type="string", nullable=false)
     */
    private $categoryName;

    /**
     * @var int
    * @Assert\NotBlank(message="la description est  obligatoire")
     * @ORM\Column(name="discription", type="string", nullable=false)
     */
    private $discription;



    /**
     * Get the value of categoryId
     *
     * @return  int
     */ 
    public function getCategoryId()
    {
        return $this->categoryId;
    }

    /**
     * Set the value of categoryId
     *
     * @param  int  $categoryId
     *
     * @return  self
     */ 
    public function setCategoryId(int $categoryId)
    {
        $this->categoryId = $categoryId;

        return $this;
    }

    /**
     * Get the value of categoryName
     *
     * @return  string
     */ 
    public function getCategoryName()
    {
        return $this->categoryName;
    }

    /**
     * Set the value of categoryName
     *
     * @param  string  $categoryName
     *
     * @return  self
     */ 
    public function setCategoryName(string $categoryName)
    {
        $this->categoryName = $categoryName;

        return $this;
    }

    /**
     * Get the value of discription
     *
     * @return  string
     */ 
    public function getDiscription()
    {
        return $this->discription;
    }

    /**
     * Set the value of discription
     *
     * @param  string  $discription
     *
     * @return  self
     */ 
    public function setDiscription(string $discription)
    {
        $this->discription = $discription;

        return $this;
    }
}
