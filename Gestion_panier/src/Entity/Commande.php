<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Commande
 *
 * @ORM\Table(name="commande")
 * @ORM\Entity
 */
class Commande
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_cmd", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCmd;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var string
     *@Assert\NotBlank(message="veuillez saisir  le mode de paiment")
     *     @Assert\Length(
     *     min=3,
     *     max=15,
     *     minMessage = "le mode de paiment doit etre supérieur a 4 caracteres",
     *     maxMessage  ="le mode de paiment ne doit pas depasser 15 caracteres",
     *     allowEmptyString = false
     *     )
     * @ORM\Column(name="modePaiment", type="string", length=30, nullable=false)
     */
    private $modepaiment;

    /**
     * @var string
     *@Assert\NotBlank(message="livraison doit etre non vide")
     * @ORM\Column(name="livraison", type="string", length=30, nullable=false)
     */
    private $livraison;

    /**
     * @var string|null
     *
     * @ORM\Column(name="etat", type="string", length=255, nullable=true)
     */
    private $etat;

    /**
     * @var int|null
     *
     * @ORM\Column(name="idProduit", type="integer", nullable=true)
     */
    private $idproduit;

    /**
     * @return int
     */
    public function getIdCmd()
    {
        return $this->idCmd;
    }

    /**
     * @param int $idCmd
     */
    public function setIdCmd($idCmd)
    {
        $this->idCmd = $idCmd;
    }

    /**
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate($date)
    {
        $this->date = $date;
    }

    /**
     * @return string
     */
    public function getModepaiment()
    {
        return $this->modepaiment;
    }

    /**
     * @param string $modepaiment
     */
    public function setModepaiment($modepaiment)
    {
        $this->modepaiment = $modepaiment;
    }

    /**
     * @return string
     */
    public function getLivraison()
    {
        return $this->livraison;
    }

    /**
     * @param string $livraison
     */
    public function setLivraison($livraison)
    {
        $this->livraison = $livraison;
    }

    /**
     * @return string|null
     */
    public function getEtat()
    {
        return $this->etat;
    }

    /**
     * @param string|null $etat
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;
    }

    /**
     * @return int|null
     */
    public function getIdproduit()
    {
        return $this->idproduit;
    }

    /**
     * @param int|null $idproduit
     */
    public function setIdproduit($idproduit)
    {
        $this->idproduit = $idproduit;
    }


}
