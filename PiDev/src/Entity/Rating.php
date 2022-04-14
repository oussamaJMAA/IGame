<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Rating
 *
 * @ORM\Table(name="rating")
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
     * @ORM\Column(name="rating_value", type="integer", nullable=false)
     */
    private $ratingValue;



    /**
     * Get the value of ratingId
     *
     * @return  int
     */ 
    public function getRatingId()
    {
        return $this->ratingId;
    }

    /**
     * Set the value of ratingId
     *
     * @param  int  $ratingId
     *
     * @return  self
     */ 
    public function setRatingId(int $ratingId)
    {
        $this->ratingId = $ratingId;

        return $this;
    }

    /**
     * Get the value of ratingValue
     *
     * @return  int
     */ 
    public function getRatingValue()
    {
        return $this->ratingValue;
    }

    /**
     * Set the value of ratingValue
     *
     * @param  int  $ratingValue
     *
     * @return  self
     */ 
    public function setRatingValue(int $ratingValue)
    {
        $this->ratingValue = $ratingValue;

        return $this;
    }
}
