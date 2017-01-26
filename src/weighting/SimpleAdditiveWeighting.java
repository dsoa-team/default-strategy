package weighting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpe.cin.dsoa.api.selector.RankStrategy;
import br.ufpe.cin.dsoa.api.service.Constraint;
import br.ufpe.cin.dsoa.api.service.ServiceInstance;

public class SimpleAdditiveWeighting implements RankStrategy {

	@Override
	public ServiceInstance ranking(List<Constraint> constraints,
			List<ServiceInstance> candidates) {

		List<ServiceInstance> filteredCandidates =  filterCandidates(constraints, candidates);
		double[][] normalized = Normalizer.normalize(constraints, filteredCandidates);
		
		Map<Double, ServiceInstance> weighted = new HashMap<Double, ServiceInstance>();
		
		//x indice dos candidates
		for(int x = 0; x < normalized.length; x++) {
			List<Constraint> candidateConstraints = filteredCandidates.get(x).getPort().getServiceSpecification().getNonFunctionalSpecification().getConstraints();
			Map<String, Constraint> mapConstraint = Normalizer.toMap(candidateConstraints);
			
			double sum = 0;
			for(int y = 0; y < normalized[0].length; y++) {
				Constraint requiredConstraint = constraints.get(y);
				double weight = mapConstraint.get(Normalizer.constraintKey(requiredConstraint)).getWeight();
				sum += weight * normalized[x][y];
			}
			
			weighted.put(sum, filteredCandidates.get(x));
		}
		
		double max = Collections.max(weighted.keySet());
		
		return weighted.get(max);
	}

	/**
	 * Filtra candidatos que não dispoem de informacoes a respeito do atritudo
	 * requerido
	 * 
	 * @param constraints
	 * @param candidates
	 * @return
	 */
	private List<ServiceInstance> filterCandidates(
			List<Constraint> constraints, List<ServiceInstance> candidates) {

		List<ServiceInstance> filtered = new ArrayList<ServiceInstance>();

		for (ServiceInstance candidate : candidates) {
			List<Constraint> candidateConstraints = candidate.getPort()
					.getServiceSpecification().getNonFunctionalSpecification()
					.getConstraints();
			Map<String, Constraint> mapConstraint = Normalizer
					.toMap(candidateConstraints);

			boolean valid = true;
			for (Constraint required : constraints) {
				if (!mapConstraint.containsKey(Normalizer
						.constraintKey(required))) {
					valid = false;
				}
			}

			if (valid) {
				filtered.add(candidate);
			} else {
				valid = true;
			}

		}

		return filtered;
	}

}
