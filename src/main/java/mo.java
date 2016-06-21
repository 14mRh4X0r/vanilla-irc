/*
 * Copyright (C) 2010-2015 Mojang AB
 *
 * Parts of this program are marked with "VanillaIRC". These will be referred
 * to as "Modification" from this point on.
 *
 * Modification Copyright (C) 2014-2015 MinecraftOnline
 *
 * The Modification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Modification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.minecraftonline.vanillairc.ChatHandler; // VanillaIRC
import com.minecraftonline.vanillairc.ObfuscationHelper; // VanillaIRC
import com.minecraftonline.vanillairc.VanillaIRC; // VanillaIRC
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class mo {
    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger f = LogManager.getLogger();
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer h;
    private final List<lt> i = Lists.newArrayList();
    private final Map<UUID, lt> j = Maps.newHashMap();
    private final mt k;
    private final ml l;
    private final mp m;
    private final mv n;
    private final Map<UUID, nq> o;
    private bag p;
    private boolean q;
    protected int e;
    private int r;
    private aia s;
    private boolean t;
    private int u;

    { ObfuscationHelper.setLoginManager(this, mo.class); } // VanillaIRC -- store static reference to object

    public mo(MinecraftServer var1) {
        this.k = new mt(a);
        this.l = new ml(b);
        this.m = new mp(c);
        this.n = new mv(d);
        this.o = Maps.newHashMap();
        this.h = var1;
        this.k.a(false);
        this.l.a(false);
        this.e = 8;
    }

    public void a(en var1, lt var2) {
        GameProfile var3 = var2.cO();
        mk var4 = this.h.aA();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        dq var7 = this.a(var2);

        var2.a((aic) this.h.a(var2.an));
        var2.c.a((lr) var2.l);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        f.info("{}[{}] logged in with entity id {} at ({}, {}, {})", new Object[] { var2.h_(), var8, Integer.valueOf(var2.O()), Double.valueOf(var2.p), Double.valueOf(var2.q), Double.valueOf(var2.r)});
        lr var9 = this.h.a(var2.an);
        azz var10 = var9.T();
        cl var11 = var9.R();

        this.a(var2, (lt) null, var9);
        md var12 = new md(this.h, var1, var2);

        var12.a((fi) (new gv(var2.O(), var2.c.b(), var10.s(), var9.s.p().a(), var9.ae(), this.p(), var10.t(), var9.U().b("reducedDebugInfo"))));
        var12.a((fi) (new gk("MC|Brand", (new ep(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((fi) (new fz(var10.x(), var10.y())));
        var12.a((fi) (new hy(var11)));
        var12.a((fi) (new ha(var2.bL)));
        var12.a((fi) (new hm(var2.bt.d)));
        this.f(var2);
        var2.E().d();
        var2.E().b(var2);
        this.a((ky) var9.ad(), var2);
        this.h.aC();
        fe var13;

        if (var2.h_().equalsIgnoreCase(var6)) {
            var13 = new fe("multiplayer.player.joined", new Object[] { var2.i_()});
        } else {
            var13 = new fe("multiplayer.player.joined.renamed", new Object[] { var2.i_(), var6});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var13.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((ex) var13);
        this.c(var2);
        var12.a(var2.p, var2.q, var2.r, var2.v, var2.w);
        this.b(var2, var9);
        if (!this.h.X().isEmpty()) {
            var2.a(this.h.X(), this.h.Y());
        }

        Iterator var14 = var2.bR().iterator();

        while (var14.hasNext()) {
            ro var15 = (ro) var14.next();

            var12.a((fi) (new ih(var2.O(), var15)));
        }

        if (var7 != null) {
            if (var7.b("RootVehicle", 10)) {
                dq var19 = var7.o("RootVehicle");
                ru var21 = ati.a(var19.o("Entity"), var9, true);

                if (var21 != null) {
                    UUID var16 = var19.a("Attach");
                    Iterator var17;
                    ru var18;

                    if (var21.be().equals(var16)) {
                        var2.a(var21, true);
                    } else {
                        var17 = var21.by().iterator();

                        while (var17.hasNext()) {
                            var18 = (ru) var17.next();
                            if (var18.be().equals(var16)) {
                                var2.a(var18, true);
                                break;
                            }
                        }
                    }

                    if (!var2.aK()) {
                        f.warn("Couldn\'t reattach entity to player");
                        var9.f(var21);
                        var17 = var21.by().iterator();

                        while (var17.hasNext()) {
                            var18 = (ru) var17.next();
                            var9.f(var18);
                        }
                    }
                }
            } else if (var7.b("Riding", 10)) {
                ru var20 = ati.a(var7.o("Riding"), var9, true);

                if (var20 != null) {
                    var2.a(var20, true);
                }
            }
        }

        var2.j_();
    }

    protected void a(ky var1, lt var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            bcd var5 = (bcd) var4.next();

            var2.a.a((fi) (new hw(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            bcc var10 = var1.a(var9);

            if (var10 != null && !var3.contains(var10)) {
                List var6 = var1.d(var10);
                Iterator var7 = var6.iterator();

                while (var7.hasNext()) {
                    fi var8 = (fi) var7.next();

                    var2.a.a(var8);
                }

                var3.add(var10);
            }
        }

    }

    public void a(lr[] var1) {
        this.p = var1[0].S().e();
        var1[0].aj().a(new asj() {
            public void a(asl var1, double var2) {
                mo.this.a((fi) (new hk(var1, hk.a.a)));
            }

            public void a(asl var1, double var2, double var4, long var6) {
                mo.this.a((fi) (new hk(var1, hk.a.b)));
            }

            public void a(asl var1, double var2, double var4) {
                mo.this.a((fi) (new hk(var1, hk.a.c)));
            }

            public void a(asl var1, int var2) {
                mo.this.a((fi) (new hk(var1, hk.a.e)));
            }

            public void b(asl var1, int var2) {
                mo.this.a((fi) (new hk(var1, hk.a.f)));
            }

            public void b(asl var1, double var2) {}

            public void c(asl var1, double var2) {}
        });
    }

    public void a(lt var1, lr var2) {
        lr var3 = var1.x();

        if (var2 != null) {
            var2.w().b(var1);
        }

        var3.w().a(var1);
        var3.r().c((int) var1.p >> 4, (int) var1.r >> 4);
    }

    public int d() {
        return lx.b(this.s());
    }

    public dq a(lt var1) {
        dq var2 = this.h.d[0].T().h();
        dq var3;

        if (var1.h_().equals(this.h.Q()) && var2 != null) {
            var3 = this.h.aI().a((oz) pa.b, (dq) var2);
            var1.f(var3);
            f.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(lt var1) {
        this.p.a(var1);
        nq var2 = (nq) this.o.get(var1.be());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(lt var1) {
        this.i.add(var1);
        this.j.put(var1.be(), var1);
        this.a((fi) (new hc(hc.a.a, new lt[] { var1})));
        lr var2 = this.h.a(var1.an);

        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            var1.a.a((fi) (new hc(hc.a.a, new lt[] { (lt) this.i.get(var3)})));
        }

        var2.a((ru) var1);
        this.a((lt) var1, (lr) null);
    }

    public void d(lt var1) {
        var1.x().w().c(var1);
    }

    public void e(lt var1) {
        lr var2 = var1.x();

        var1.b((nr) nv.f);
        this.b(var1);
        if (var1.aK()) {
            ru var3 = var1.bz();

            if (var3.b(lt.class).size() == 1) {
                f.debug("Removing player mount");
                var1.p();
                var2.f(var3);
                Iterator var4 = var3.by().iterator();

                while (var4.hasNext()) {
                    ru var5 = (ru) var4.next();

                    var2.f(var5);
                }

                var2.a(var1.ac, var1.ae).e();
            }
        }

        var2.e(var1);
        var2.w().b(var1);
        this.i.remove(var1);
        UUID var6 = var1.be();
        lt var7 = (lt) this.j.get(var6);

        if (var7 == var1) {
            this.j.remove(var6);
            this.o.remove(var6);
        }

        this.a((fi) (new hc(hc.a.e, new lt[] { var1})));
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            mu var5 = (mu) ((ms) this.k).b(var2); // VanillaIRC -- obfuscation anomaly: cast this.k to ms

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            mm var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + g.format(var3.c());
            }

            return var4;
        } else {
            return this.i.size() >= this.e && !this.f(var2) ? "The server is full!" : null;
        }
    }

    public lt g(GameProfile var1) {
        UUID var2 = zr.a(var1);
        ArrayList var3 = Lists.newArrayList();

        for (int var4 = 0; var4 < this.i.size(); ++var4) {
            lt var5 = (lt) this.i.get(var4);

            if (var5.be().equals(var2)) {
                var3.add(var5);
            }
        }

        lt var7 = (lt) this.j.get(var1.getId());

        if (var7 != null && !var3.contains(var7)) {
            var3.add(var7);
        }

        Iterator var8 = var3.iterator();

        while (var8.hasNext()) {
            lt var6 = (lt) var8.next();

            var6.a.c("You logged in from another location");
        }

        Object var9;

        if (this.h.V()) {
            var9 = new lm(this.h.a(0));
        } else {
            var9 = new lu(this.h.a(0));
        }

        return new lt(this.h, this.h.a(0), var1, (lu) var9);
    }

    public lt a(lt var1, int var2, boolean var3) {
        var1.x().v().b(var1);
        var1.x().v().b((ru) var1);
        var1.x().w().b(var1);
        this.i.remove(var1);
        this.h.a(var1.an).f(var1);
        cl var4 = var1.cS();
        boolean var5 = var1.cT();

        var1.an = var2;
        Object var6;

        if (this.h.V()) {
            var6 = new lm(this.h.a(var1.an));
        } else {
            var6 = new lu(this.h.a(var1.an));
        }

        lt var7 = new lt(this.h, this.h.a(var1.an), var1.cO(), (lu) var6);

        var7.a = var1.a;
        var7.a((zr) var1, var3);
        var7.f(var1.O());
        var7.v(var1);
        var7.a((sd) var1.cv());
        Iterator var8 = var1.P().iterator();

        while (var8.hasNext()) {
            String var9 = (String) var8.next();

            var7.a((String) var9);
        }

        lr var10 = this.h.a(var1.an);

        this.a(var7, var1, var10);
        cl var11;

        if (var4 != null) {
            var11 = zr.a(this.h.a(var1.an), var4, var5);
            if (var11 != null) {
                var7.b((double) ((float) var11.p() + 0.5F), (double) ((float) var11.q() + 0.1F), (double) ((float) var11.r() + 0.5F), 0.0F, 0.0F);
                var7.a((cl) var4, var5);
            } else {
                var7.a.a((fi) (new gq(0, 0.0F)));
            }
        }

        var10.r().c((int) var7.p >> 4, (int) var7.r >> 4);

        while (!var10.a((ru) var7, (bbx) var7.bo()).isEmpty() && var7.q < 256.0D) {
            var7.b(var7.p, var7.q + 1.0D, var7.r);
        }

        var7.a.a((fi) (new hi(var7.an, var7.l.ae(), var7.l.T().t(), var7.c.b())));
        var11 = var10.R();
        var7.a.a(var7.p, var7.q, var7.r, var7.v, var7.w);
        var7.a.a((fi) (new hy(var11)));
        var7.a.a((fi) (new hs(var7.bO, var7.bN, var7.bM)));
        this.b(var7, var10);
        this.f(var7);
        var10.w().a(var7);
        var10.a((ru) var7);
        this.i.add(var7);
        this.j.put(var7.be(), var7);
        var7.j_();
        var7.c(var7.bT());
        return var7;
    }

    public void f(lt var1) {
        GameProfile var2 = var1.cO();
        int var3 = this.h(var2) ? this.m.a(var2) : 0;

        var3 = this.h.R() && this.h.d[0].T().u() ? 4 : var3;
        var3 = this.t ? 4 : var3;
        this.b(var1, var3);
    }

    public void a(lt var1, int var2) {
        int var3 = var1.an;
        lr var4 = this.h.a(var1.an);

        var1.an = var2;
        lr var5 = this.h.a(var1.an);

        var1.a.a((fi) (new hi(var1.an, var1.l.ae(), var1.l.T().t(), var1.c.b())));
        this.f(var1);
        var4.f(var1);
        var1.F = false;
        this.a(var1, var3, var4, var5);
        this.a(var1, var4);
        var1.a.a(var1.p, var1.q, var1.r, var1.v, var1.w);
        var1.c.a(var5);
        var1.a.a((fi) (new ha(var1.bL)));
        this.b(var1, var5);
        this.g(var1);
        Iterator var6 = var1.bR().iterator();

        while (var6.hasNext()) {
            ro var7 = (ro) var6.next();

            var1.a.a((fi) (new ih(var1.O(), var7)));
        }

    }

    public void a(ru var1, int var2, lr var3, lr var4) {
        double var5 = var1.p;
        double var7 = var1.r;
        double var9 = 8.0D;
        float var11 = var1.v;

        var3.C.a("moving");
        if (var1.an == -1) {
            var5 = oo.a(var5 / 8.0D, var4.aj().b() + 16.0D, var4.aj().d() - 16.0D);
            var7 = oo.a(var7 / 8.0D, var4.aj().c() + 16.0D, var4.aj().e() - 16.0D);
            var1.b(var5, var1.q, var7, var1.v, var1.w);
            if (var1.au()) {
                var3.a(var1, false);
            }
        } else if (var1.an == 0) {
            var5 = oo.a(var5 * 8.0D, var4.aj().b() + 16.0D, var4.aj().d() - 16.0D);
            var7 = oo.a(var7 * 8.0D, var4.aj().c() + 16.0D, var4.aj().e() - 16.0D);
            var1.b(var5, var1.q, var7, var1.v, var1.w);
            if (var1.au()) {
                var3.a(var1, false);
            }
        } else {
            cl var12;

            if (var2 == 1) {
                var12 = var4.R();
            } else {
                var12 = var4.p();
            }

            var5 = (double) var12.p();
            var1.q = (double) var12.q();
            var7 = (double) var12.r();
            var1.b(var5, var1.q, var7, 90.0F, 0.0F);
            if (var1.au()) {
                var3.a(var1, false);
            }
        }

        var3.C.b();
        if (var2 != 1) {
            var3.C.a("placing");
            var5 = (double) oo.a((int) var5, -29999872, 29999872);
            var7 = (double) oo.a((int) var7, -29999872, 29999872);
            if (var1.au()) {
                var1.b(var5, var1.q, var7, var1.v, var1.w);
                var4.x().a(var1, var11);
                var4.a(var1);
                var4.a(var1, false);
            }

            var3.C.b();
        }

        var1.a((aic) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((fi) (new hc(hc.a.c, this.i)));
            this.u = 0;
        }

    }

    public void a(fi<?> var1) {
        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            ((lt) this.i.get(var2)).a.a(var1);
        }

    }

    public void a(fi<?> var1, int var2) {
        for (int var3 = 0; var3 < this.i.size(); ++var3) {
            lt var4 = (lt) this.i.get(var3);

            if (var4.an == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(zr var1, ex var2) {
        bci var3 = var1.aQ();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                lt var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(zr var1, ex var2) {
        bci var3 = var1.aQ();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.i.size(); ++var4) {
                lt var5 = (lt) this.i.get(var4);

                if (var5.aQ() != var3) {
                    var5.a(var2);
                }
            }

        }
    }

    public String b(boolean var1) {
        String var2 = "";
        ArrayList var3 = Lists.newArrayList(this.i);

        for (int var4 = 0; var4 < var3.size(); ++var4) {
            if (var4 > 0) {
                var2 = var2 + ", ";
            }

            var2 = var2 + ((lt) var3.get(var4)).h_();
            if (var1) {
                var2 = var2 + " (" + ((lt) var3.get(var4)).bf() + ")";
            }
        }

        return var2;
    }

    public String[] f() {
        String[] var1 = new String[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lt) this.i.get(var2)).h_();
        }

        return var1;
    }

    public GameProfile[] g() {
        GameProfile[] var1 = new GameProfile[this.i.size()];

        for (int var2 = 0; var2 < this.i.size(); ++var2) {
            var1[var2] = ((lt) this.i.get(var2)).cO();
        }

        return var1;
    }

    public mt h() {
        return this.k;
    }

    public ml i() {
        return this.l;
    }

    public void a(GameProfile var1) {
        int var2 = this.h.q();

        ((ms) this.m).a((mr) (new mq(var1, this.h.q(), this.m.b(var1)))); // VanillaIRC -- obfuscation anomaly: cast this.m to ms
        this.b(this.a(var1.getId()), var2);
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
        this.b(this.a(var1.getId()), 0);
    }

    private void b(lt var1, int var2) {
        if (var1 != null && var1.a != null) {
            byte var3;

            if (var2 <= 0) {
                var3 = 24;
            } else if (var2 >= 4) {
                var3 = 28;
            } else {
                var3 = (byte) (24 + var2);
            }

            var1.a.a((fi) (new gn(var1, var3)));
        }

    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean h(GameProfile var1) {
        return this.m.d(var1) || this.h.R() && this.h.d[0].T().u() && this.h.Q().equalsIgnoreCase(var1.getName()) || this.t;
    }

    @Nullable
    public lt a(String var1) {
        Iterator var2 = this.i.iterator();

        lt var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (lt) var2.next();
        } while (!var3.h_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(@Nullable zr var1, double var2, double var4, double var6, double var8, int var10, fi<?> var11) {
        for (int var12 = 0; var12 < this.i.size(); ++var12) {
            lt var13 = (lt) this.i.get(var12);

            if (var13 != var1 && var13.an == var10) {
                double var14 = var2 - var13.p;
                double var16 = var4 - var13.q;
                double var18 = var6 - var13.r;

                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.a(var11);
                }
            }
        }

    }

    public void j() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            this.b((lt) this.i.get(var1));
        }

    }

    public void d(GameProfile var1) {
        ((ms) this.n).a((mr) (new mw(var1))); // VanillaIRC -- obfuscation anomaly: cast this.m to ms
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public mv k() {
        return this.n;
    }

    public String[] l() {
        return this.n.a();
    }

    public mp m() {
        return this.m;
    }

    public String[] n() {
        return this.m.a();
    }

    public void a() {}

    public void b(lt var1, lr var2) {
        asl var3 = this.h.d[0].aj();

        var1.a.a((fi) (new hk(var3, hk.a.d)));
        var1.a.a((fi) (new hz(var2.P(), var2.Q(), var2.U().b("doDaylightCycle"))));
        if (var2.W()) {
            var1.a.a((fi) (new gq(1, 0.0F)));
            var1.a.a((fi) (new gq(7, var2.j(1.0F))));
            var1.a.a((fi) (new gq(8, var2.h(1.0F))));
        }

    }

    public void g(lt var1) {
        var1.a(var1.bu);
        var1.u();
        var1.a.a((fi) (new hm(var1.bt.d)));
    }

    public int o() {
        return this.i.size();
    }

    public int p() {
        return this.e;
    }

    public String[] q() {
        return this.h.d[0].S().e().f();
    }

    public boolean r() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List<lt> b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.i.iterator();

        while (var3.hasNext()) {
            lt var4 = (lt) var3.next();

            if (var4.A().equals(var1)) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public int s() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.h;
    }

    public dq t() {
        return null;
    }

    private void a(lt var1, lt var2, aic var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.T().q());
    }

    public void u() {
        for (int var1 = 0; var1 < this.i.size(); ++var1) {
            ((lt) this.i.get(var1)).a.c("Server closed");
        }

    }

    // VanillaIRC start -- redirect for chat injection
    public void a(ex var1, boolean var2) {
        this.sendMessageToPlayers(var1, var2);
        
        String translated = var1.c();
        ChatHandler ch = VanillaIRC.getHandler();
        if (ch != null) {
            ch.handleGameMessage(translated);
        }
    }
    
    public void sendMessageToPlayers(ex var1, boolean var2) {
        this.h.a(var1);
        int var3 = var2 ? 1 : 0;

        this.a((fi) (new gb(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(ex.a.a(json), false);
    } // VanillaIRC end

    public void a(ex var1) {
        this.a(var1, true);
    }

    public nq a(zr var1) {
        UUID var2 = var1.be();
        nq var3 = var2 == null ? null : (nq) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.h.a(0).S().b(), "stats");
            File var5 = new File(var4, var2 + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.h_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new nq(this.h, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.h.d != null) {
            lr[] var2 = this.h.d;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                lr var5 = var2[var4];

                if (var5 != null) {
                    var5.w().a(var1);
                    var5.v().a(var1);
                }
            }

        }
    }

    public List<lt> v() {
        return this.i;
    }

    public lt a(UUID var1) {
        return (lt) this.j.get(var1);
    }

    public boolean f(GameProfile var1) {
        return false;
    }
}
