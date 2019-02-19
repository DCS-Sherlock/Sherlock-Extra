package uk.ac.warwick.dcs.sherlock.module.model.extra;

import uk.ac.warwick.dcs.sherlock.api.annotation.AdjustableParameter;
import uk.ac.warwick.dcs.sherlock.api.common.IndexedString;
import uk.ac.warwick.dcs.sherlock.api.model.detection.AbstractPairwiseDetector;
import uk.ac.warwick.dcs.sherlock.api.model.detection.AbstractPairwiseDetectorWorker;
import uk.ac.warwick.dcs.sherlock.api.model.detection.DetectorRank;
import uk.ac.warwick.dcs.sherlock.api.model.preprocessing.PreProcessingStrategy;
import uk.ac.warwick.dcs.sherlock.module.model.base.postprocessing.SimpleObjectEqualityRawResult;
import uk.ac.warwick.dcs.sherlock.module.model.base.preprocessing.VariableExtractor;
import uk.ac.warwick.dcs.sherlock.module.model.extra.TestDetector2.TestDetectorWorker2;

import java.util.*;

public class TestDetector2 extends AbstractPairwiseDetector<TestDetectorWorker2> {

	@Override
	public TestDetectorWorker2 getAbstractPairwiseDetectorWorker() {
		return new TestDetector2.TestDetectorWorker2();
	}

	@Override
	public String getDisplayName() {
		return "Test Detector";
	}

	@Override
	public List<PreProcessingStrategy> getPreProcessors() {
		return Collections.singletonList(PreProcessingStrategy.of("variables", VariableExtractor.class));
	}

	@Override
	public DetectorRank getRank() {
		return DetectorRank.PRIMARY;
	}

	public class TestDetectorWorker2 extends AbstractPairwiseDetectorWorker<SimpleObjectEqualityRawResult<String>> {

		@Override
		public void execute() {
			// This detector finds and matches up variables - it only works on declarations of the variable, not every time the variable is called.

			List<IndexedString> linesF1 = this.file1.getPreProcessedLines("variables");
			List<IndexedString> linesF2 = this.file2.getPreProcessedLines("variables");

			SimpleObjectEqualityRawResult<String> res = new SimpleObjectEqualityRawResult<>(this.file1.getFile(), this.file2.getFile(), linesF1.size(), linesF2.size());

			for (IndexedString checkLine : linesF1) {
				linesF2.stream().filter(x -> x.valueEquals(checkLine)).forEach(x -> res.put(checkLine.getValue(), checkLine.getKey(), x.getKey()));
			}

			this.result = res;
		}
	}
}

