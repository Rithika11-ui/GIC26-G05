import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AuthModule } from './auth/auth.module';
import { UsersModule } from './users/users.module';
import { FarmersModule } from './farmers/farmers.module';
import { ProductsModule } from './products/products.module';
import { OrdersModule } from './orders/orders.module';
import { RewardsModule } from './rewards/rewards.module';
import { RewardsService } from './#/rewards/rewards.service';
import { AdminModule } from './admin/admin.module';

@Module({
  imports: [AuthModule, UsersModule, FarmersModule, ProductsModule, OrdersModule, RewardsModule, AdminModule],
  controllers: [AppController],
  providers: [AppService, RewardsService],
})
export class AppModule {}
