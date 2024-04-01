import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HpMainComponent } from './hp-main.component';

describe('HpMainComponent', () => {
  let component: HpMainComponent;
  let fixture: ComponentFixture<HpMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HpMainComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HpMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
