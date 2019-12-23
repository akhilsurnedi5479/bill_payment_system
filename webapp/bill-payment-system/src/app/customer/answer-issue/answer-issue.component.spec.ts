import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerIssueComponent } from './answer-issue.component';

describe('AnswerIssueComponent', () => {
  let component: AnswerIssueComponent;
  let fixture: ComponentFixture<AnswerIssueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnswerIssueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerIssueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
