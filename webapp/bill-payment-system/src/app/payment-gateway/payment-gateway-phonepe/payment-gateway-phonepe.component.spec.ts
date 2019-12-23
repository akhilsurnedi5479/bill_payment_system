import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentGatewayPhonepeComponent } from './payment-gateway-phonepe.component';

describe('PaymentGatewayPhonepeComponent', () => {
  let component: PaymentGatewayPhonepeComponent;
  let fixture: ComponentFixture<PaymentGatewayPhonepeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentGatewayPhonepeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentGatewayPhonepeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
