<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Flag
 *
 * @ORM\Table(name="flag")
 * @ORM\Entity
 */
class Flag
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;


}
