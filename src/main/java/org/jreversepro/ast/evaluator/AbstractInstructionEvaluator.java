/**
 * JReversePro - Java Decompiler / Disassembler.
 * Copyright (C) 2008 Karthik Kumar.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *  
 *  	http://www.apache.org/licenses/LICENSE-2.0 
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */
package org.jreversepro.ast.evaluator;

import java.util.logging.Logger;

import org.jreversepro.CustomLoggerFactory;
import org.jreversepro.ast.expression.ConditionExpression;
import org.jreversepro.ast.intermediate.ConditionalLine;
import org.jreversepro.ast.intermediate.LineOfCodeList;
import org.jreversepro.jls.JLSConstants;
import org.jreversepro.jvm.JVMConstants;
import org.jreversepro.jvm.Opcodes;
import org.jreversepro.reflect.ConstantPool;
import org.jreversepro.reflect.instruction.Instruction;
import org.jreversepro.reflect.variabletable.VariableTable;


/**
 * Evaluates the instructions (byte codes) as appropriate.
 * 
 * @author karthik.kumar
 * 
 */
public abstract class AbstractInstructionEvaluator implements JVMConstants,
    JLSConstants, Opcodes {

  public AbstractInstructionEvaluator(EvaluatorContext context) {
    pool = context.pool;
    varTable = context.varTable;
    evalMachine = context.evalMachine;
    statements = context.statements;

  }

  abstract Iterable<Integer> getProcessingOpcodes();

  abstract void evaluate(Instruction ins);



  protected void addConditional(Instruction ins, ConditionExpression expr) {
    statements.append(new ConditionalLine(ins, expr));
  }

  protected ConstantPool pool;
  protected VariableTable varTable;
  protected String methodReturnType;
  protected LineOfCodeList statements;
  protected EvaluatorMachine evalMachine;

  protected static final Logger logger = CustomLoggerFactory.createLogger();

}
