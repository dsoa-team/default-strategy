package weighting;

import java.util.List;

import br.ufpe.cin.dsoa.api.service.Constraint;
import br.ufpe.cin.dsoa.api.service.ServiceInstance;

public class Normalizer {

	public static double[][] normalize(List<Constraint> constraints,
			List<ServiceInstance> candidates) {

		double matrix[][] = new double[candidates.size()][constraints.size()];

		for (int x = 0; x < constraints.size(); x++) {
			
			System.out.println(":: CONSTRAINT" + constraints.get(x) + " ::");
			for (int y = 0; y < candidates.size(); y++) {
				
				ServiceInstance candidate = candidates.get(y);
				System.out.println(":: CANDIDATE " + candidate.getName() + " ::");
				List<Constraint> candidateConstraints = candidate.getPort().getServiceSpecification().getNonFunctionalSpecification().getConstraints();
				for(Constraint c : candidateConstraints){
					System.out.println("attr: " + c.getAttributeId());
					System.out.println("op: " + c.getOperation());
					System.out.println("th: " + c.getThreashold());
					System.out.println("w: " + c.getWeight());
					System.out.println("ro: " + c.getExpression());
					System.out.println();
					System.out.println("=======================");
				}
				System.out.println();
				System.out.println();
			}
		}
		return matrix;
	}

	/*private Map<String, Constraint> toMap(List<Constraint> constraints) {

		Map<String, Constraint> map = new HashMap<String, Constraint>();

		for (Constraint constraint : constraints) {
			
		}

		return map;
	}*/
}