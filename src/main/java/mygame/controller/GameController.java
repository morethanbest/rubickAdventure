package mygame.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mygame.game.BattleField;
import mygame.util.LogTracker;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class GameController {

    @Resource
    private BattleField battleField;

    @Resource
    private LogTracker logTracker;

    @RequestMapping("startGame")
    public String startGame(@RequestParam("type") String characterType){
        battleField.initHero(characterType);
        return "mygame";
    }

    @RequestMapping("refreshHero")
    @ResponseBody
    public JSONObject refreshHero(){
        return battleField.getHeroInfo();
    }

    @RequestMapping("refreshEnemy")
    @ResponseBody
    public JSONObject refreshEnemy(){
        JSONObject enemyNow = battleField.getEnemyNowInfo();
        return enemyNow;
    }

    @RequestMapping("getBattleLog")
    @ResponseBody
    public JSONArray getBattleLog(){
        return logTracker.getLogs();
    }

    @RequestMapping("next")
    @ResponseBody
    public boolean next(){
        battleField.nextTurn();
        return true;
    }

    @RequestMapping("fight")
    @ResponseBody
    public boolean fight(){
        return battleField.fight();
    }

    @RequestMapping("refreshBagInfo")
    @ResponseBody
    public JSONArray refreshBagInfo(){
        return battleField.getHeroBagInfo();
    }

    @RequestMapping("getItemInfo")
    @ResponseBody
    public JSONObject getItemInfo(@RequestParam int index){
        return battleField.getBagItemInfoByIndex(index);
    }

    @RequestMapping("getSlotInfo")
    @ResponseBody
    public JSONObject getSlotInfo(){
        return battleField.getSlotInfo();
    }

    @RequestMapping("getSlotItemInfo")
    @ResponseBody
    public JSONObject getSlotItemInfo(@RequestParam int slot){
        JSONObject object = battleField.getSlotItemInfo(slot);
        return object;
    }


    @RequestMapping("equip")
    @ResponseBody
    public int equip(@RequestParam int index){
        return battleField.equip(index);
    }

    @RequestMapping("unLoad")
    @ResponseBody
    public boolean unLoad(@RequestParam int index){
        battleField.unLoad(index);
        return true;
    }

    @RequestMapping("enhance")
    @ResponseBody
    public boolean enhance(@RequestParam int index){
        return battleField.enhance(index);
    }

    @RequestMapping("sell")
    @ResponseBody
    public boolean sell(@RequestParam int index){
        battleField.sell(index);
        return true;
    }

    @RequestMapping("getGold")
    @ResponseBody
    public JSONObject getGold(){
        JSONObject object = new JSONObject();
        object.put("gold", battleField.getGold());
        return object;
    }
}
