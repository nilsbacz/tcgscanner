package com.nb.tcgscanner.controller;

import com.nb.tcgscanner.card.*;
import com.nb.tcgscanner.card.trait.*;
import com.nb.tcgscanner.service.CardTextService;
import com.nb.tcgscanner.service.OcrService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @PostMapping("/scan")
    public ResponseEntity<Map<String, Object>> scanCard(@RequestParam("file") MultipartFile file) {
        // Process the image file and extract card details
        // This is a placeholder for the image processing logic
        Card card;
        try {
            card = processCardImage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a response map
        Map<String, Object> response = new HashMap<>();
        response.put(card.getClass().getSimpleName(),card);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Card processCardImage(MultipartFile file) throws IOException {

        ArrayList<AttackCost> attackCostList = new ArrayList<>();
        attackCostList.add(new AttackCost(1, ElementalType.NORMAL));

        // Examples: Returning dummy cards for demonstration purposes

        EnergyCard energyCard = EnergyCard.builder()
                .withElementalType(ElementalType.FIRE)
                .withAbility(new Ability("pewpew", "incinerates the opponent"))
                .build();

        TrainerCard trainerCard = TrainerCard.builder()
                .withName("Hop")
                .withTrainerType(TrainerType.SUPPORT)
                .withDescription("Ziehe 3 Karten")
                .build();

        StadiumCard stadiumCard = StadiumCard.builder()
                .withName("Stadium")
                .withDescription("Stadium description")
                .build();

        //todo make dpi and lang depend on user input
        OcrService ocrService = new OcrService();
        ocrService.setDpi(300);
        ocrService.setLang("deu");
        CardTextService cardTextService = new CardTextService(ocrService);

        Map<String, String> cardTexts = cardTextService.processCard(file);

        PokemonCard pokemonCard = PokemonCard.builder()
                .withName(cardTexts.get("name").trim())
                .withElementalType(ElementalType.LIGHTNING)
                .withAbility(new Ability("Thunderbolt", "Deals damage to the opponent"))
                .withHp(Integer.parseInt(cardTexts.get("hp").trim()))
                .withAttack(new Attack("name1", 10, "desc1", attackCostList))
                .withAttackNoCosts("name2", 20, "desc2")
                .build();

        return pokemonCard;
    }
}