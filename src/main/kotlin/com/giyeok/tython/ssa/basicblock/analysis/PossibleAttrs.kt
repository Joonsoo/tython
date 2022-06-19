package com.giyeok.tython.ssa.basicblock.analysis

// 함수나 클래스는 CreateFunctionDef, CreateClassDef의 dest를 identifier로 한다
// 함수나 클래스 내부에서
class PossibleAttrs {
}

// 어떤 값(SSA Var로 identify되는)의 attribute가 어떤것들이 될 수 있는지
// StoreAttribute가 어떤식으로 호출되는지 파악
// TODO setattr에서 attribute 이름이 상수면 StoreAttribute로 만들기, getattr도 마찬가지
// LoadAttribute도 활용할 수 있을까?

// 같은 CFG 안에서 특정한 SSAVar를 사용하는 지점을 추적
class UsesOf {

}

// 한 SSAVar가 가질 수 있는 값을 추적
// 결과의 종류는
// 1. 주어진 그래프에서 완전히 추적 가능한 값(e.g. 그 안에서 정의된 함수나 클래스 reference, 상수 등)
// 2. 외부의 값에 따라 달라지는 값(e.g. parameter, nonlocal, global 등에 영향을 받는 값)
// -> 외부의 값을 정해주고 돌려서 값을 추가로 얻을 수 있도록
// TODO "값"을 어떻게 정의할까?
// - 상수
// - 상수들로 이루어진 연산식
// - 변수/변수들로 이루어진 연산식
class PossibleValuesOfVar {

}


// SSAVar로 특정되는 객체의 attribute들이 가질 수 있는 값. 결과는 PossibleValuesOfVar와 같은 형태일 것
class PossibleValuesOfAttrs {

}


// SSAVar로 특정되는 튜플의 element들이 가질 수 있는 값. 튜플의 특성상 각 element를 따로 추적.
// 결과는 PossibleValuesOfVar와 같은 형태일 것
class PossibleValuesOfTupleElem {

}

// SSAVar로 특정되는 리스트의 element들이 가질 수 있는 값. element들의 위치는 관계 없이 추적.
// 결과는 PossibleValuesOfVar와 같은 형태일 것.
class PossibleValuesOfListElem {

}


// 주어진 CFG들 안에서 SSAVar 이름으로 특정된 함수나 클래스를 호출하는 지점 추적
class CallersOf {

}


// TODO "지점"의 개념이 없음. "dest: SSAVar"가 없는 op들도 있기 때문에 SSA statement의 "위치" 개념을 만들 필요가 있음
// -> (nodeId, stmt idx)가 적절해 보임
