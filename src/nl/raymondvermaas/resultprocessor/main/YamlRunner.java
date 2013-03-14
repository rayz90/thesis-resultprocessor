package nl.raymondvermaas.resultprocessor.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import nl.raymondvermaas.resultprocessor.models.AttackFraudCombination;
import nl.raymondvermaas.resultprocessor.models.BaseCriterion;
import nl.raymondvermaas.resultprocessor.models.Impact;
import nl.raymondvermaas.resultprocessor.models.Scenario;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlRunner {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		if( args.length != 1 ) {
            System.out.println( "Usage: <file.yaml>" );
            return;
        }
  
		YamlConfig config = null;  
        try( InputStream in = Files.newInputStream( Paths.get( args[ 0 ] ) ) ) {
        	Constructor constructor = new Constructor(YamlConfig.class);
        	
        	TypeDescription configDescription = new TypeDescription(YamlConfig.class);
        	configDescription.putMapPropertyType("basecriteria", String.class, BaseCriterion.class);
        	configDescription.putListPropertyType("scenarios", Scenario.class);
        	configDescription.putListPropertyType("fraudscenarios", Impact.class);
        	configDescription.putMapPropertyType("attackfraudmatrix",  AttackFraudCombination.class, Integer.class);
        	constructor.addTypeDescription(configDescription);
        	
        	TypeDescription bcDescription = new TypeDescription(BaseCriterion.class);
        	bcDescription.putListPropertyType("map", Double.class);
        	bcDescription.putListPropertyType("borders", Double.class);
        	constructor.addTypeDescription(bcDescription);
        	
        	Yaml yaml = new Yaml(constructor);
        	config = (YamlConfig) yaml.load( in );
        } catch (IOException e) {
			e.printStackTrace();
		}
        ResultProcessor rp = new ResultProcessor(config.getExperts(), config.getBasecriteria(), (Scenario[]) config.getScenarios().toArray(new Scenario[1]));

		Scenario[] risks = rp.loadFromCsv(config.getInputcsv());
		
		if(config.isCalculateImpact()) {
			risks = rp.getRiskScenarios((ArrayList<Impact>) config.getFraudscenarios(), config.getAttackfraudmatrix());
		}
			
		System.out.println(rp.toString(risks));

		rp.exportToXml(risks, config.getBasexml(), config.getResultxml());

	}

}
